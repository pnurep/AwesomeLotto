package com.dev.gold.awesomelotto.ui.widget.listAdapter

import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.dev.gold.awesomelotto.ui.widget.viewholder.BaseViewHolder


abstract class BaseListAdapter<T>(
    val data: ObservableList<T>
) : RecyclerView.Adapter<BaseViewHolder<*, *>>() {

    protected open val onListChangedCallback =
        object : ObservableList.OnListChangedCallback<ObservableList<T>>() {

            override fun onChanged(sectionItems: ObservableList<T>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sectionItems: ObservableList<T>,
                start: Int,
                count: Int
            ) {
                notifyItemRangeChanged(start, count)
            }

            override fun onItemRangeInserted(
                sectionItems: ObservableList<T>,
                start: Int,
                count: Int
            ) {
                notifyItemRangeInserted(start, count)
            }

            override fun onItemRangeMoved(
                sectionItems: ObservableList<T>,
                from: Int,
                to: Int,
                count: Int
            ) {
            }

            override fun onItemRangeRemoved(
                sectionItems: ObservableList<T>,
                start: Int,
                count: Int
            ) {
                notifyItemRangeRemoved(start, count)
            }
        }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<*, *>) {
        holder.onAttach()
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<*, *>) {
        holder.onDetach()
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: BaseViewHolder<*, *>) {
        holder.onDestroy()
        super.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: BaseViewHolder<*, *>): Boolean {
        holder.onDestroy()
        return super.onFailedToRecycleView(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.data.addOnListChangedCallback(onListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)

        data.removeOnListChangedCallback(onListChangedCallback)
    }
}
