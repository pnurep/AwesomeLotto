package com.dev.gold.awesomelotto.ui

import android.os.Bundle
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.databinding.ActivityMainBinding
import com.dev.gold.awesomelotto.viewmodels.MainViewModel
import javax.inject.Inject


class MainActivity(
    override val layoutId: Int = R.layout.activity_main
) : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}
