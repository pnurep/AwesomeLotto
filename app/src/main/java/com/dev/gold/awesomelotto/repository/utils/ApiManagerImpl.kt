package com.dev.gold.awesomelotto.repository.utils

import com.google.gson.Gson
import okhttp3.RequestBody
import retrofit2.Retrofit


class ApiManagerImpl(
    private val retrofit: Retrofit
) : ApiManager {

    override fun <T> get(apiClass: Class<T>): T = retrofit.create(apiClass)

    override fun Map<String, Any?>.toRequestBody(): RequestBody =
        RequestBody.create(ApiManager.JSON, Gson().toJson(this))
}
