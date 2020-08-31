package com.dev.gold.awesomelotto

import android.app.Application
import com.dev.gold.awesomelotto.di.DaggerApplicationComponent
import com.dev.gold.awesomelotto.utils.TaskStateManager
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject


class AlApplication : Application(), HasAndroidInjector {

    @Inject
    @Volatile
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        TaskStateManager.init(this)

        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)

        setRxGlobalErrorHandler()

        getApplicationComponent()
    }

    private fun getApplicationComponent() {
        DaggerApplicationComponent.factory().create(this).inject(this)
    }

    private fun setRxGlobalErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            var error = e
            if (error is UndeliverableException)
                error = e.cause

            if (error is IOException || error is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (error is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (error is NullPointerException || error is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
            if (error is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
        }
    }

    override fun onTerminate() {
        TaskStateManager.clear(this)

        super.onTerminate()
    }

    companion object {
        lateinit var instance: AlApplication
            private set
    }
}
