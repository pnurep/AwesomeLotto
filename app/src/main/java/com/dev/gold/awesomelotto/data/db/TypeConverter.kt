package com.dev.gold.awesomelotto.data.db

import androidx.room.TypeConverter
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


object TypeConverter {

    @TypeConverter
    @JvmStatic
    fun intListToString(list: List<Int>): String = Gson().toJson(list)

    @TypeConverter
    @JvmStatic
    fun toIntList(value: String?): List<Int> = if (value.isNullOrEmpty())
        listOf()
    else
        Gson().fromJson(value, object : TypeToken<List<Int>>() {}.type)

    @TypeConverter
    @JvmStatic
    fun toDate(value: Long?) = value?.let { Date(value) }

    @TypeConverter
    @JvmStatic
    fun fromDate(value: Date?): Long? = value?.time

    @TypeConverter
    @JvmStatic
    fun fromStringToLotto(value: String?): Lotto? =
        Gson().fromJson(value, object : TypeToken<Lotto>() {}.type)

    @TypeConverter
    @JvmStatic
    fun fromLottoToString(value: Lotto?): String? = Gson().toJson(value)
}
