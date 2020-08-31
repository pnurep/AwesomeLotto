package com.dev.gold.awesomelotto.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "lotto")
class Lotto {

    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id")
    var id: Int = -1

    @field:SerializedName("numbers")
    var numbers: List<Int> = listOf()
}
