package com.dev.gold.awesomelotto.ui.widget.listAdapter

import android.view.ViewGroup
import androidx.databinding.ObservableList
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.ui.widget.viewholder.BaseViewHolder
import com.dev.gold.awesomelotto.ui.widget.viewholder.LottoViewHolder
import com.dev.gold.awesomelotto.viewmodels.BaseViewModel
import com.dev.gold.awesomelotto.viewmodels.listItem.LottoListItemViewModel
import javax.inject.Provider


class GeneratedNumberListAdapter(
    listData: ObservableList<Any>,
    private val viewModelProvider: Provider<LottoListItemViewModel>
) : BaseListAdapter<Any>(listData) {

    override fun getItemCount(): Int = data.size

    private fun getItem(index: Int) = data.getOrNull(index)

    override fun getItemViewType(position: Int) = when (val item = getItem(position)) {
        is String ->
            VIEW_TYPE_TITLE
        is Lotto ->
            VIEW_TYPE_LOTTO
        else ->
            error(item.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            VIEW_TYPE_TITLE ->
                object : BaseViewHolder<String, BaseViewModel>(
                    parent,
                    R.layout.holder_list_title
                ) {
                    override fun setItem(item: String) {
                        super.setItem(item)
                        binding.setVariable(BR.title, item)
                    }
                }
            VIEW_TYPE_LOTTO ->
                LottoViewHolder(
                    parent,
                    viewModelProvider
                )
            else ->
                error(viewType.toString())
        }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_TITLE ->
                (holder as BaseViewHolder<String, out BaseViewModel>)
                    .setItem(getItem(position) as String)
            VIEW_TYPE_LOTTO ->
                (holder as LottoViewHolder)
                    .setItem(getItem(position) as Lotto)
        }
    }

    companion object {
        private const val VIEW_TYPE_TITLE = 0
        private const val VIEW_TYPE_LOTTO = 1
    }
}
