package com.dev.gold.awesomelotto.ui.widget.viewholder

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.data.dto.WinningAndLotto
import com.dev.gold.awesomelotto.viewmodels.BaseViewModel


class PastWinningViewHolder(
    parent: ViewGroup,
    @LayoutRes
    layout: Int
) : BaseViewHolder<WinningAndLotto, BaseViewModel>(parent, layout) {

    override fun setItem(item: WinningAndLotto) {
        super.setItem(item)

        binding.setVariable(BR.winning, item.winning)
        binding.setVariable(BR.lotto, item.lotto)
        binding.executePendingBindings()
    }
}
