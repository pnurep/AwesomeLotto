package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.db.dao.WinningDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.data.dto.Winning
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.repository.api.WinningApi
import com.dev.gold.awesomelotto.repository.utils.ApiManager
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class WinningRepositoryImpl(
    apiManager: ApiManager,
    private val winningDao: WinningDao
) : WinningRepository,
    ApiManager by apiManager {

    private val api = apiManager[WinningApi::class.java]

    override fun getWinningById(id: Int): Single<Winning> =
        winningDao.getWinningById(id)

    override fun getWinningByDrawNumber(drwNo: Int): Single<Winning> {
        return api.getWinning(drwNo)
            .subscribeOn(Schedulers.io())
            .flatMap { response ->

                val winningNumber = Lotto().apply {
                    numbers = listOf(
                        response.drwtNo1, response.drwtNo2, response.drwtNo3,
                        response.drwtNo4, response.drwtNo5, response.drwtNo6
                    )
                }

                val winning = Winning().apply {
                    id = response.drwNo
                    date = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).parse(response.drwNoDate)
                    this.winningNumber = winningNumber
                    this.bonusNumber = response.bnusNo
                }

                // save call result
                setWinnings(listOf(winning))

                // load from DB
                getWinningById(winning.id)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllWinnings(): Flowable<List<Winning>> =
        winningDao.loadWinnings()

    override fun setWinnings(winnings: List<Winning>) =
        winningDao.insert(winnings)

    override fun deleteAllWinnings(): Single<Int> =
        winningDao.deleteAll()
}
