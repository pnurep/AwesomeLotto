package com.dev.gold.awesomelotto.repository

import com.dev.gold.awesomelotto.data.dto.Winning
import io.reactivex.Flowable
import io.reactivex.Single


interface WinningRepository {

    fun getWinning(
        id: Int
    ): Single<Winning>

    fun getAllWinnings(): Flowable<List<Winning>>

    fun setWinnings(
        winnings: List<Winning>
    ): Single<List<Long>>

    fun deleteAllWinnings(): Single<Int>
}
