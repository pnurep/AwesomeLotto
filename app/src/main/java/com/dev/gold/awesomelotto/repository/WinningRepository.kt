package com.dev.gold.awesomelotto.repository

import com.dev.gold.awesomelotto.data.dto.Winning
import io.reactivex.Flowable
import io.reactivex.Single


interface WinningRepository {

    fun getWinningById(
        id: Int
    ): Single<Winning>

    fun getWinningByDrawNumber(
        drwNo: Int
    ): Single<Winning>

    fun getAllWinnings(): Flowable<List<Winning>>

    fun setWinnings(winnings: List<Winning>)

    fun deleteAllWinnings(): Single<Int>
}
