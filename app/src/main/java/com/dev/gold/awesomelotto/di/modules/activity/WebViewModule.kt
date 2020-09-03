package com.dev.gold.awesomelotto.di.modules.activity

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.di.ViewModelKey
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.ui.activity.WebViewActivity
import com.dev.gold.awesomelotto.viewmodels.WebViewViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
object WebViewModule {

    @Provides
    @ActivityScope
    fun provideActivity(
        activity: WebViewActivity
    ): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun provideViewModelProvider(
        viewModelStoreOwner: WebViewActivity,
        viewModelFactory: ViewModelProvider.Factory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @ActivityScope
    fun provideWebViewViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): WebViewViewModel =
        viewModelProvider.get(WebViewViewModel::class.java)

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(WebViewViewModel::class)
    fun provideViewModel(): ViewModel =
        WebViewViewModel()

}