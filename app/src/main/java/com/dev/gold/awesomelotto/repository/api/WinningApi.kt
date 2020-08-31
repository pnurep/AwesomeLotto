package com.dev.gold.awesomelotto.repository.api

import com.dev.gold.awesomelotto.data.dao.ResponseLotto
import io.reactivex.Single
import retrofit2.http.*


@JvmSuppressWildcards
interface WinningApi {

    @GET("common.do?method=getLottoNumber")
    fun getWinning(
        @Query("drwNo")
        drwNo: Int
    ): Single<ResponseLotto>
}
