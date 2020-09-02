package com.dev.gold.awesomelotto.viewmodels

import com.dev.gold.awesomelotto.ui.activity.MainActivity
import com.dev.gold.awesomelotto.utils.NavigationHandler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SplashViewModel(
    private val navigationHandler: NavigationHandler
) : BaseViewModel() {

    init {

        Observable.just(Unit)
            .subscribeOn(Schedulers.io())
            .map {
                TimeUnit.SECONDS.sleep(2L)
            }
            .flatMapSingle {
                navigationHandler.goTo(MainActivity::class.java)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeAlter {
                navigationHandler.activityFinish()
            }
    }
}
