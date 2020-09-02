package com.dev.gold.awesomelotto.ui.widget.viewholder

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView


abstract class AbsViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root), LifecycleOwner