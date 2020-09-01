package com.dev.gold.awesomelotto.data.dao

import com.google.gson.annotations.SerializedName

//{
//  "totSellamnt": 96962255000,
//  "returnValue": "success",
//  "drwNoDate": "2020-01-25",
//  "firstWinamnt": 1928079219,
//  "drwtNo6": 41,
//  "drwtNo4": 38,
//  "firstPrzwnerCo": 12,
//  "drwtNo5": 39,
//  "bnusNo": 23,
//  "firstAccumamnt": 23136950628,
//  "drwNo": 895,
//  "drwtNo2": 26,
//  "drwtNo3": 31,
//  "drwtNo1": 16
//}
data class ResponseWinning(
    @SerializedName("totSellamnt")
    val totSellamnt: Long,

    @SerializedName("returnValue")
    val returnValue: String,

    @SerializedName("drwNoDate")
    val drwNoDate: String,

    @SerializedName("firstWinamnt")
    val firstWinamnt: Long,

    @SerializedName("drwNo")
    val drwNo: Int,

    @SerializedName("drwtNo1")
    val drwtNo1: Int,

    @SerializedName("drwtNo2")
    val drwtNo2: Int,

    @SerializedName("drwtNo3")
    val drwtNo3: Int,

    @SerializedName("drwtNo4")
    val drwtNo4: Int,

    @SerializedName("drwtNo5")
    val drwtNo5: Int,

    @SerializedName("drwtNo6")
    val drwtNo6: Int,

    @SerializedName("bnusNo")
    val bnusNo: Int,

    @SerializedName("firstAccumamnt")
    val firstAccumamnt: Long,

    @SerializedName("firstPrzwnerCo")
    val firstPrzwnerCo: Int
)
