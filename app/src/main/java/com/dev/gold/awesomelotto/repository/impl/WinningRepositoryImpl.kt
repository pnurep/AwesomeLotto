package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.dao.WinningDao
import com.dev.gold.awesomelotto.data.dto.Winning
import com.dev.gold.awesomelotto.repository.WinningRepository
import io.reactivex.Flowable
import io.reactivex.Single


class WinningRepositoryImpl(
    private val winningDao: WinningDao
) : WinningRepository {

    override fun getWinning(id: Int): Single<Winning> =
        winningDao.getWinningById(id)

    override fun getAllWinnings(): Flowable<List<Winning>> =
        winningDao.loadWinnings()

    override fun setWinnings(winnings: List<Winning>): Single<List<Long>> =
        winningDao.insert(winnings)

    override fun deleteAllWinnings(): Single<Int> =
        winningDao.deleteAll()
}
