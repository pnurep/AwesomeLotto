package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.db.dao.LottoDao
import com.dev.gold.awesomelotto.data.db.dao.WinningDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.data.dto.Winning
import com.dev.gold.awesomelotto.data.dto.WinningAndLotto
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.repository.api.WinningApi
import com.dev.gold.awesomelotto.repository.utils.ApiManager
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class WinningRepositoryImpl(
    apiManager: ApiManager,
    private val winningDao: WinningDao,
    private val lottoDao: LottoDao
) : WinningRepository,
    ApiManager by apiManager {

    private val api = apiManager[WinningApi::class.java]

    override fun getWinningById(id: Int): Winning? =
        winningDao.getWinningById(id)

    override fun getWinningAndLottoById(lottoId: Int): Single<WinningAndLotto> =
        winningDao.getWinningAndLottoById(lottoId)

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
            .flatMap { lottoId ->
                getWinningAndLottoById(lottoId.toInt())
            }
            .observeOn(AndroidSchedulers.mainThread())

    override fun updateAndGetAllWinnings(
        latestDrwNo: Int
    ): Flowable<List<Winning>> {
        // TODO()
    }

    override fun getAllWinnings(): Flowable<List<Winning>> =
        winningDao.loadWinnings()

    override fun setWinning(winning: Winning) =
        winningDao.insert(winning)

    override fun deleteAllWinnings(): Single<Int> =
        winningDao.deleteAll()

    override fun deleteWinningById(winningId: Int): Single<Int> {
        return winningDao.deleteById(winningId)
    }
}
