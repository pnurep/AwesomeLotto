package com.dev.gold.awesomelotto.ui.activity

import android.os.Bundle
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.databinding.ActivityLottoGenerationBinding
import com.dev.gold.awesomelotto.ui.widget.listAdapter.LottoGenerationListAdapter
import com.dev.gold.awesomelotto.viewmodels.LottoGenerationViewModel
import javax.inject.Inject


class LottoGenerationActivity(
    override val layoutId: Int = R.layout.activity_lotto_generation
) : BaseActivity<ActivityLottoGenerationBinding>() {

    @Inject
    lateinit var viewModel: LottoGenerationViewModel

    @Inject
    lateinit var listAdapter: LottoGenerationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.listAdapter, listAdapter)
        binding.executePendingBindings()
    }
}
