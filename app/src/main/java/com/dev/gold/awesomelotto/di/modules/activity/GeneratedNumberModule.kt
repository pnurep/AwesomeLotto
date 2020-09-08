package com.dev.gold.awesomelotto.di.modules.activity

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.di.ViewModelKey
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.repository.LottoRepository
import com.dev.gold.awesomelotto.ui.activity.GeneratedNumberActivity
import com.dev.gold.awesomelotto.ui.widget.listAdapter.GeneratedNumberListAdapter
import com.dev.gold.awesomelotto.viewmodels.GeneratedNumberViewModel
import com.dev.gold.awesomelotto.viewmodels.listItem.LottoListItemViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider


@Module
object GeneratedNumberModule {

    @Provides
    @ActivityScope
    fun provideListData(): ObservableList<Any> = ObservableArrayList()

    @Provides
    @ActivityScope
    fun provideActivity(
        activity: GeneratedNumberActivity
    ): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun provideViewModelProvider(
        viewModelStoreOwner: GeneratedNumberActivity,
        viewModelFactory: ViewModelProvider.Factory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @ActivityScope
    fun provideGeneratedNumberViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): GeneratedNumberViewModel = viewModelProvider.get(GeneratedNumberViewModel::class.java)

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(GeneratedNumberViewModel::class)
    fun provideViewModel(
        data: ObservableList<Any>,
        lottoRepository: LottoRepository
    ): ViewModel =
        GeneratedNumberViewModel(
            data,
            lottoRepository
        )

    @Provides
    fun provideLottoListItemViewModel() = LottoListItemViewModel()

    @Provides
    @ActivityScope
    fun provideListAdapter(
        data: ObservableList<Any>,
        viewModelProvider: Provider<LottoListItemViewModel>
    ) = GeneratedNumberListAdapter(
        data,
        viewModelProvider
    )
}
