package com.dev.gold.awesomelotto.data.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity(tableName = "winning", primaryKeys = ["id"])
class Winning {

    @field:SerializedName("id")
    var id : Int = -1

    @field:SerializedName("date")
    var date : Date? = null

    @field:SerializedName("winningNumber")
    var winningNumber: Lotto = Lotto()

    @field:SerializedName("bonus")
    var bonusNumber: Int = -1
}
