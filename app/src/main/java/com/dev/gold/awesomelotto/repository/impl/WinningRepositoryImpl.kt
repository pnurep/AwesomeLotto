package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.db.dao.LottoDao
import com.dev.gold.awesomelotto.data.db.dao.WinningDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.data.dto.Winning
import com.dev.gold.awesomelotto.data.dto.WinningAndLotto
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.repository.api.WinningApi
import com.dev.gold.awesomelotto.repository.utils.ApiManager
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class WinningRepositoryImpl(
    apiManager: ApiManager,
    private val winningDao: WinningDao,
    private val lottoDao: LottoDao
) : WinningRepository,
    ApiManager by apiManager {

    private val api = apiManager[WinningApi::class.java]

    override fun getWinningById(
        id: Int
    ): Winning? = winningDao.getWinningById(id)

    override fun getWinningAndLottoById(
        lottoId: Int
    ): Single<WinningAndLotto> = winningDao.getWinningAndLottoById(lottoId)

    override fun getAllWinningAndLotto(
        isAsc: Boolean
    ): Maybe<List<WinningAndLotto>> = winningDao.getAllWinningAndLotto(isAsc)

    override fun getWinningByDrawNumber(drwNo: Int) =
        api.getWinning(drwNo)
            .subscribeOn(Schedulers.io())
            .map { response ->

                // load from DB
                getWinningById(drwNo)?.let { savedWinning ->
                    lottoDao.deleteById(savedWinning.winningLottoId)
                }

                val lottoId = lottoDao.insert(
                    Lotto().apply {
                        numbers = listOf(
                            response.drwtNo1, response.drwtNo2, response.drwtNo3,
                            response.drwtNo4, response.drwtNo5, response.drwtNo6
                        )
                        date = Date()
                    }
                )

                val winning = Winning().apply {
                    id = response.drwNo
                    date = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).parse(response.drwNoDate)
                    winningLottoId = lottoId.toInt()
                    bonusNumber = response.bnusNo
                }

                winningDao.insert(winning)

                lottoId
            }

    override fun updateAndGetAllWinnings(
        latestDrwNo: Int
    ): Maybe<List<Winning>> =
        getAllWinnings()
            .subscribeOn(Schedulers.io())
            .flatMap { savedList ->
                if (savedList.size < latestDrwNo) {
                    Observable
                        .fromIterable((1..latestDrwNo).toList())
                        .concatMapEager { drwNo ->
                            TimeUnit.MILLISECONDS.sleep(250)
                            val savedWinning = winningDao.getWinningById(drwNo)
                            if (savedWinning == null) {
                                getWinningByDrawNumber(drwNo)
                                    .flatMapObservable {
                                        Observable.just(winningDao.getWinningById(drwNo)!!)
                                    }
                            } else {
                                Observable.just(savedWinning)
                            }
                        }
                        .toList()
                        .flatMapMaybe {
                            Maybe.just(it)
                        }
                } else {
                    Maybe.just(savedList)
                }
            }

    override fun getAllWinnings() = winningDao.loadWinnings()

    override fun setWinning(
        winning: Winning
    ) = winningDao.insert(winning)

    override fun deleteAllWinnings(): Single<Int> =
        winningDao.deleteAll()

    override fun deleteWinningById(
        winningId: Int
    ) = winningDao.deleteById(winningId)
}
