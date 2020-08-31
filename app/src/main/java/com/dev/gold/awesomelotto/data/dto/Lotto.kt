package com.dev.gold.awesomelotto.data.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "lotto", primaryKeys = ["id"])
class Lotto {

    @field:SerializedName("id")
    var id : Int = -1

    @field:SerializedName("numbers")
    var numbers: List<Int> = listOf()
}
