package com.dev.gold.awesomelotto.repository.utils

import okhttp3.MediaType
import okhttp3.RequestBody


interface ApiManager {

    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    operator fun <T> get(apiClass: Class<T>): T

    fun Map<String, Any?>.toRequestBody(): RequestBody
}
