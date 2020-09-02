package com.dev.gold.awesomelotto.di

import com.dev.gold.awesomelotto.di.modules.ViewModelFactoryModule
import com.dev.gold.awesomelotto.di.modules.activity.GeneratedNumberModule
import com.dev.gold.awesomelotto.di.modules.activity.LottoGenerationModule
import com.dev.gold.awesomelotto.di.modules.activity.MainModule
import com.dev.gold.awesomelotto.di.modules.activity.SplashModule
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.ui.activity.GeneratedNumberActivity
import com.dev.gold.awesomelotto.ui.activity.LottoGenerationActivity
import com.dev.gold.awesomelotto.ui.activity.MainActivity
import com.dev.gold.awesomelotto.ui.activity.SplashActivity
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

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class, LottoGenerationModule::class])
    abstract fun provideLottoGenerationActivity(): LottoGenerationActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class, GeneratedNumberModule::class])
    abstract fun provideGeneratedNumberActivity(): GeneratedNumberActivity
}
