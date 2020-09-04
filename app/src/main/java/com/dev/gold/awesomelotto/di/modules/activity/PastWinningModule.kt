package com.dev.gold.awesomelotto.di.modules.activity

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.di.ViewModelKey
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.ui.activity.PastWinningActivity
import com.dev.gold.awesomelotto.viewmodels.PastWinningViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
object PastWinningModule {

    @Provides
    @ActivityScope
    fun provideListData(): ObservableList<Any> = ObservableArrayList()

    @Provides
    @ActivityScope
    fun provideActivity(
        activity: PastWinningActivity
    ): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun provideViewModelProvider(
        viewModelStoreOwner: PastWinningActivity,
        viewModelFactory: ViewModelProvider.Factory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @ActivityScope
    fun providePastWinningViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): PastWinningViewModel = viewModelProvider.get(PastWinningViewModel::class.java)

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(PastWinningViewModel::class)
    fun provideViewModel(): ViewModel =
        PastWinningViewModel()
}
