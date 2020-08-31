package com.dev.gold.awesomelotto.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.databinding.ActivitySplashBinding
import com.dev.gold.awesomelotto.utils.OnTaskChangeListener
import com.dev.gold.awesomelotto.utils.TaskStateManager
import com.dev.gold.awesomelotto.viewmodels.SplashViewModel
import javax.inject.Inject


class SplashActivity(
    override val layoutId: Int = R.layout.activity_splash
) : BaseActivity<ActivitySplashBinding>(), OnTaskChangeListener {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TaskStateManager.addOnTaskChangeListener(this)
        TaskStateManager.onCreate(this)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        TaskStateManager.onNewIntent(this)
    }

    override fun onResume() {
        super.onResume()
        TaskStateManager.onResume()
    }

    override fun onStop() {
        super.onStop()
        TaskStateManager.onStop(this)
    }

    public override fun onDestroy() {
        TaskStateManager.onDestroy(this)
        TaskStateManager.removeOnTaskChangeListener(this)
        super.onDestroy()
    }

    override fun onTaskForeground() {}

    override fun onTaskBackground() {}
}
