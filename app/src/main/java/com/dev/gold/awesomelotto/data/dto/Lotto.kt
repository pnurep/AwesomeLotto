package com.dev.gold.awesomelotto.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dev.gold.awesomelotto.data.db.TypeConverter


@Entity
class Lotto {

    @PrimaryKey(autoGenerate = true)
    var lottoId: Int = 0

    @TypeConverters(TypeConverter::class)
    var numbers: List<Int> = listOf()
}
