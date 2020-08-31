package com.dev.gold.awesomelotto.data.dto

import androidx.room.Entity
import androidx.room.TypeConverters
import com.dev.gold.awesomelotto.data.db.TypeConverter
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity(tableName = "winning", primaryKeys = ["id"])
class Winning {

    @field:SerializedName("id")
    var id: Int = -1

    @field:SerializedName("date")
    @TypeConverters(TypeConverter::class)
    var date: Date? = null

    @field:SerializedName("winningNumber")
    @TypeConverters(TypeConverter::class)
    var winningNumber: Lotto = Lotto()

    @field:SerializedName("bonus")
    var bonusNumber: Int = -1
}
