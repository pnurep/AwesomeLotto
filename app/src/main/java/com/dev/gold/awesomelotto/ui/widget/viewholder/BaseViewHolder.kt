package com.dev.gold.awesomelotto.ui.widget.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.dev.gold.awesomelotto.viewmodels.BaseViewModel


open class BaseViewHolder<T, VM : BaseViewModel>(
    parent: ViewGroup,
    @LayoutRes layout: Int,
) : AbsViewHolder(
    DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layout,
        parent,
        false
    )
) {

    private val lifecycleRegistry = LifecycleRegistry(this)

    protected var viewModel: VM? = null

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    open fun makeViewModel(item: T): VM? = null

    @CallSuper
    open fun setItem(item: T) {
        binding.lifecycleOwner = this
        viewModel = makeViewModel(item)
    }

    @CallSuper
    open fun onAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
        (viewModel as? BaseViewModel)?.onResume()
    }

    @CallSuper
    open fun onDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        (viewModel as? BaseViewModel)?.onPause()
    }

    @CallSuper
    open fun onDestroy() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        viewModel?.onCleared()
    }
}
