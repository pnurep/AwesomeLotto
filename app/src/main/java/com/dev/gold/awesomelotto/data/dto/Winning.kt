package com.dev.gold.awesomelotto.data.dto

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
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
    ],
    indices = [Index("winningLottoId")]
)
class Winning {

    @ColumnInfo(name = "winning_id")
    var id: Int = 0

    @TypeConverters(TypeConverter::class)
    var date: Date? = null

    var winningLottoId: Int = -1

    var bonusNumber: Int = -1
}
