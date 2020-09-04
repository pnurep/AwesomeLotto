package com.dev.gold.awesomelotto.ui.activity

import android.os.Bundle
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.databinding.ActivityPastWinningBinding
import com.dev.gold.awesomelotto.viewmodels.PastWinningViewModel
import javax.inject.Inject


class PastWinningActivity(
    override val layoutId: Int = R.layout.activity_past_winning
) : BaseActivity<ActivityPastWinningBinding>() {

    @Inject
    lateinit var viewModel: PastWinningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}
