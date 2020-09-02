package com.dev.gold.awesomelotto.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.TypeConverters
import com.dev.gold.awesomelotto.data.db.TypeConverter
import java.util.*


@Entity(
    primaryKeys = ["winning_id"],
    foreignKeys = [
        ForeignKey(
            entity = Lotto::class,
            parentColumns = ["lottoId"],
            childColumns = ["winningLottoId"],
            onDelete = CASCADE
        )
    ]
)
class Winning {

    @ColumnInfo(name = "winning_id")
    var id: Int = 0

    @TypeConverters(TypeConverter::class)
    var date: Date? = null

    var winningLottoId: Int = -1

    var bonusNumber: Int = -1
}
