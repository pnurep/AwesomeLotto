package com.dev.gold.awesomelotto.di.modules.activity

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.di.ViewModelKey
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.ui.activity.SplashActivity
import com.dev.gold.awesomelotto.utils.NavigationHandler
import com.dev.gold.awesomelotto.viewmodels.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
object SplashModule {

    @Provides
    @ActivityScope
    fun provideActivity(
        activity: SplashActivity
    ): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun provideNavigationHandler(
        activity: SplashActivity
    ): NavigationHandler = activity

    @Provides
    @ActivityScope
    fun provideViewModelProvider(
        viewModelStoreOwner: SplashActivity,
        viewModelFactory: ViewModelProvider.Factory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @ActivityScope
    fun provideSplashViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): SplashViewModel = viewModelProvider.get(SplashViewModel::class.java)

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(SplashViewModel::class)
    fun provideViewModel(
        navigationHandler: NavigationHandler
    ): ViewModel = SplashViewModel(navigationHandler)
}
