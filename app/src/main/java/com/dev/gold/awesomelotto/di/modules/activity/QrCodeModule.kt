package com.dev.gold.awesomelotto.di.modules.activity

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.di.ViewModelKey
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.ui.activity.QrCodeActivity
import com.dev.gold.awesomelotto.viewmodels.QrCodeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
object QrCodeModule {

    @Provides
    @ActivityScope
    fun provideActivity(
        activity: QrCodeActivity
    ): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun provideViewModelProvider(
        viewModelStoreOwner: QrCodeActivity,
        viewModelFactory: ViewModelProvider.Factory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @ActivityScope
    fun provideQrCodeViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): QrCodeViewModel =
        viewModelProvider.get(QrCodeViewModel::class.java)

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(QrCodeViewModel::class)
    fun provideViewModel(): ViewModel =
        QrCodeViewModel()
}
