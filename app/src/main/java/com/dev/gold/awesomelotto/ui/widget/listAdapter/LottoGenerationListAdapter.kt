package com.dev.gold.awesomelotto.ui.widget.listAdapter

import android.view.ViewGroup
import androidx.databinding.ObservableList
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.ui.widget.viewholder.BaseViewHolder
import com.dev.gold.awesomelotto.ui.widget.viewholder.LottoViewHolder
import com.dev.gold.awesomelotto.viewmodels.listItem.LottoListItemViewModel
import javax.inject.Provider


class LottoGenerationListAdapter(
    listData: ObservableList<Lotto>,
    private val viewModelProvider: Provider<LottoListItemViewModel>
) : BaseListAdapter<Lotto>(listData) {

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LottoViewHolder(
            parent,
            viewModelProvider
        )

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        (holder as LottoViewHolder).setItem(data[position])
        holder.binding.executePendingBindings()
    }
}
