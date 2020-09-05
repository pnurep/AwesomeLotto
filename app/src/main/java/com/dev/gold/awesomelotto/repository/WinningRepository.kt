package com.dev.gold.awesomelotto.repository

import com.dev.gold.awesomelotto.data.dto.Winning
import com.dev.gold.awesomelotto.data.dto.WinningAndLotto
import io.reactivex.Maybe
import io.reactivex.Single


interface WinningRepository {

    fun getWinningById(
        id: Int
    ): Winning?

    fun getWinningAndLottoById(
        lottoId: Int
    ): Single<WinningAndLotto>

    fun getWinningByDrawNumber(
        drwNo: Int
    ): Single<Long>

    fun updateAndGetAllWinnings(
        latestDrwNo: Int
    ): Maybe<List<Winning>>

    fun getAllWinnings(): Maybe<List<Winning>>

    fun setWinning(winning: Winning): Long

    fun deleteAllWinnings(): Single<Int>

    fun deleteWinningById(winningId: Int): Single<Int>
}
