package com.dev.gold.awesomelotto.ui.activity

import android.os.Bundle
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.databinding.ActivityGeneratedNumberBinding
import com.dev.gold.awesomelotto.ui.widget.listAdapter.GeneratedNumberListAdapter
import com.dev.gold.awesomelotto.viewmodels.GeneratedNumberViewModel
import javax.inject.Inject


class GeneratedNumberActivity(
    override val layoutId: Int = R.layout.activity_generated_number
) : BaseActivity<ActivityGeneratedNumberBinding>() {

    @Inject
    lateinit var viewModel: GeneratedNumberViewModel

    @Inject
    lateinit var listAdapter: GeneratedNumberListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.listAdapter, listAdapter)
        binding.executePendingBindings()
    }
}