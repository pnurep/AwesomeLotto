package com.dev.gold.awesomelotto.data

import com.dev.gold.awesomelotto.ui.activity.*


const val APPLICATION_CONTEXT = "ApplicationContext"

const val FIRST_GAME_START_DATE = "2002-12-07 23:59:59"

enum class ActivityConstants(val target: Class<*>) {

    SPLASH(SplashActivity::class.java),

    MAIN(MainActivity::class.java),

    GENERATION(LottoGenerationActivity::class.java),

    GENERATED(GeneratedNumberActivity::class.java),

    QRCODE(QrCodeActivity::class.java),

    WEBVIEW(WebViewActivity::class.java),

    PASTWINNING(PastWinningActivity::class.java)
}