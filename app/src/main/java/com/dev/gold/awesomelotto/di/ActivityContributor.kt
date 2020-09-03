package com.dev.gold.awesomelotto.di

import com.dev.gold.awesomelotto.di.modules.ViewModelFactoryModule
import com.dev.gold.awesomelotto.di.modules.activity.*
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.ui.activity.*
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

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class, QrCodeModule::class])
    abstract fun provideQrCodeActivity(): QrCodeActivity
}
