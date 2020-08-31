package com.dev.gold.awesomelotto.utils

import android.os.Bundle
import com.dev.gold.awesomelotto.data.ActivityResults
import io.reactivex.Single


interface NavigationHandler {

    fun goTo(
        targetActivityClass: Class<*>,
        args: Bundle = Bundle(),
        requireResult: Boolean = false
    ): Single<ActivityResults>

    fun activityFinish()
}
