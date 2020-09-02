package com.dev.gold.awesomelotto.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.dev.gold.awesomelotto.AlApplication
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.databinding.ActivityMainBinding
import com.dev.gold.awesomelotto.viewmodels.MainViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class MainActivity(
    override val layoutId: Int = R.layout.activity_main
) : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var viewModel: MainViewModel

    private val backButtonSubject = BehaviorSubject.createDefault(0L).toSerialized()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        observeBackButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            binding.drawer.openDrawer(GravityCompat.START)
        else
            super.onOptionsItemSelected(item)

        return true
    }

    override fun onBackPressed() {
        when {
            binding.drawer.isDrawerOpen(GravityCompat.START) ->
                binding.drawer.closeDrawer(GravityCompat.START)
            else ->
                backButtonSubject.onNext(System.currentTimeMillis())
        }
    }

    private fun observeBackButton() {
        backButtonSubject.toFlowable(BackpressureStrategy.BUFFER)
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(2, 1)
            .map { it[0] to it[1] }
            .subscribeAlter { time ->
                if (time.second - time.first < 1000) {
                    finish()
                } else {
                    Toast.makeText(
                        AlApplication.instance,
                        "\'뒤로\' 버튼을 한번 더 누르면 종료됩니다",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
