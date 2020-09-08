package com.dev.gold.awesomelotto.ui.widget.listAdapter

import android.view.ViewGroup
import androidx.databinding.ObservableList
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.data.dto.WinningAndLotto
import com.dev.gold.awesomelotto.ui.widget.viewholder.BaseViewHolder
import com.dev.gold.awesomelotto.ui.widget.viewholder.PastWinningViewHolder


class PastWinningListAdapter(
    listData: ObservableList<WinningAndLotto>
) : BaseListAdapter<WinningAndLotto>(listData) {

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PastWinningViewHolder(
            parent = parent,
            layout = R.layout.holder_winning
        )

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        (holder as PastWinningViewHolder).setItem(data[position])
    }
}
