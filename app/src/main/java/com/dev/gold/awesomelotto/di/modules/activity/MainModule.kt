package com.dev.gold.awesomelotto.di.modules.activity

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.di.ViewModelKey
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.ui.activity.MainActivity
import com.dev.gold.awesomelotto.utils.NavigationHandler
import com.dev.gold.awesomelotto.viewmodels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
object MainModule {

    @Provides
    @ActivityScope
    fun provideActivity(
        activity: MainActivity
    ): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun provideNavigationHandler(
        activity: MainActivity
    ): NavigationHandler = activity

    @Provides
    @ActivityScope
    fun provideViewModelProvider(
        viewModelStoreOwner: MainActivity,
        viewModelFactory: ViewModelProvider.Factory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @ActivityScope
    fun provideMainViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): MainViewModel = viewModelProvider.get(MainViewModel::class.java)

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(MainViewModel::class)
    fun provideViewModel(
        navigationHandler: NavigationHandler,
        winningRepository: WinningRepository
    ): ViewModel =
        MainViewModel(
            navigationHandler,
            winningRepository
        )
}
