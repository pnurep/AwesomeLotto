package com.dev.gold.awesomelotto.di.modules.activity

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.di.ViewModelKey
import com.dev.gold.awesomelotto.di.scopes.ActivityScope
import com.dev.gold.awesomelotto.repository.LottoGenerationRepository
import com.dev.gold.awesomelotto.ui.activity.LottoGenerationActivity
import com.dev.gold.awesomelotto.ui.widget.listAdapter.LottoGenerationListAdapter
import com.dev.gold.awesomelotto.viewmodels.LottoGenerationViewModel
import com.dev.gold.awesomelotto.viewmodels.listItem.LottoListItemViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider


@Module
object LottoGenerationModule {

    @Provides
    @ActivityScope
    fun provideListData(): ObservableList<Lotto> = ObservableArrayList()

    @Provides
    @ActivityScope
    fun provideActivity(
        activity: LottoGenerationActivity
    ): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun provideViewModelProvider(
        viewModelStoreOwner: LottoGenerationActivity,
        viewModelFactory: ViewModelProvider.Factory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @ActivityScope
    fun provideLottoGenerationViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): LottoGenerationViewModel =
        viewModelProvider.get(LottoGenerationViewModel::class.java)

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(LottoGenerationViewModel::class)
    fun provideViewModel(
        data: ObservableList<Lotto>,
        lottoGenerationRepository: LottoGenerationRepository
    ): ViewModel =
        LottoGenerationViewModel(
            data,
            lottoGenerationRepository
        )

    @Provides
    fun provideLottoListItemViewModel() = LottoListItemViewModel()

    @Provides
    @ActivityScope
    fun provideListAdapter(
        data: ObservableList<Lotto>,
        viewModelProvider: Provider<LottoListItemViewModel>
    ) = LottoGenerationListAdapter(
        data,
        viewModelProvider
    )
}
