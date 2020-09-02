package com.dev.gold.awesomelotto.data.dto

import androidx.room.Embedded
import androidx.room.Relation


data class WinningAndLotto(
    @Embedded val lotto: Lotto,
    @Relation(
        parentColumn = "lottoId",
        entityColumn = "winningLottoId"
    )
    val winning: Winning
)
