package com.dev.gold.awesomelotto.di

import com.dev.gold.awesomelotto.di.modules.ViewModelFactoryModule
import com.dev.gold.awesomelotto.di.modules.activity.MainModule
import com.dev.gold.awesomelotto.di.modules.activity.SplashModule
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.ui.MainActivity
import com.dev.gold.awesomelotto.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityContributor {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class, SplashModule::class])
    abstract fun provideSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class, MainModule::class])
    abstract fun provideMainActivity(): MainActivity
}
