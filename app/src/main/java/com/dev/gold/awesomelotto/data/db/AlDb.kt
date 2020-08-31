package com.dev.gold.awesomelotto.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev.gold.awesomelotto.data.db.dao.LottoDao
import com.dev.gold.awesomelotto.data.db.dao.WinningDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.data.dto.Winning


@Database(
    entities = [
        Lotto::class,
        Winning::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AlDb : RoomDatabase() {

    abstract fun lottoDao(): LottoDao

    abstract fun winningDao(): WinningDao
}
