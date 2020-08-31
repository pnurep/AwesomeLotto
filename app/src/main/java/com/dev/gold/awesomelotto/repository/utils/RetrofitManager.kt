package com.dev.gold.awesomelotto.repository.utils

import com.dev.gold.awesomelotto.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


object RetrofitManager {

    private const val TIME_OUT = 20_000L
    private const val CORE_POOL_SIZE = 5
    private const val MAX_POOL_SIZE = 30
    private const val KEEP_ALIVE_TIME = 15L

    fun build(
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.dhlottery.co.kr/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .callbackExecutor(
            ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                SynchronousQueue()
            )
        )
        .client(getOkHttpClient())
        .build()

    private fun getOkHttpClient() =
        OkHttpClient.Builder()
            .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )

                    addNetworkInterceptor(StethoInterceptor())
                }
            }.build()
}
