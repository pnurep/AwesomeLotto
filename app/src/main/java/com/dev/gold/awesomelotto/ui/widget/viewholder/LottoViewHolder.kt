package com.dev.gold.awesomelotto.ui.widget.viewholder

import android.view.ViewGroup
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.viewmodels.listItem.LottoListItemViewModel
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.R
import javax.inject.Provider


class LottoViewHolder(
    parent: ViewGroup,
    private val viewModelProvider: Provider<LottoListItemViewModel>
) : BaseViewHolder<Lotto, LottoListItemViewModel>(parent, R.layout.holder_lotto) {

    override fun makeViewModel(item: Lotto) =
        viewModelProvider.get().apply { bind(item) }

    override fun setItem(item: Lotto) {
        super.setItem(item)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}