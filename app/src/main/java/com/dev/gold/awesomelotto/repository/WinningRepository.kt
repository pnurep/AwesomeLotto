package com.dev.gold.awesomelotto.repository

import com.dev.gold.awesomelotto.data.dto.Winning
import com.dev.gold.awesomelotto.data.dto.WinningAndLotto
import io.reactivex.Flowable
import io.reactivex.Single


interface WinningRepository {

    fun getWinningById(
        id: Int
    ): Winning?

    fun getWinningAndLottoById(
        id: Int
    ): Single<WinningAndLotto>

    fun getWinningByDrawNumber(
        drwNo: Int
    ): Single<WinningAndLotto>

    fun getAllWinnings(): Flowable<List<Winning>>

    fun setWinning(winning: Winning): Long

    fun deleteAllWinnings(): Single<Int>
}
