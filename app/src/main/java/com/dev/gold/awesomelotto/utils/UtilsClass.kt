package com.dev.gold.awesomelotto.utils

import com.dev.gold.awesomelotto.data.FIRST_GAME_START_DATE
import java.text.SimpleDateFormat
import java.util.*


object UtilsClass {

    fun getLatestDrawNumber() =
        SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss",
            Locale.getDefault()
        ).parse(FIRST_GAME_START_DATE)
            ?.let { startDate ->
                val diff: Long = Date().time - startDate.time
                val latest = diff / (86400 * 1000 * 7) + 1
                latest.toInt()
            } ?: 1
}
