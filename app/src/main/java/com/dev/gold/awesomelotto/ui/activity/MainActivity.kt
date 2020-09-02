package com.dev.gold.awesomelotto.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            binding.drawer.openDrawer(GravityCompat.START)
        else
            super.onOptionsItemSelected(item)

        return true
    }
}
