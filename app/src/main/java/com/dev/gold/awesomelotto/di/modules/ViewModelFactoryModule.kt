package com.dev.gold.awesomelotto.di.modules

import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory
}
