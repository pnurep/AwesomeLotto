package com.dev.gold.awesomelotto.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dev.gold.awesomelotto.data.ActivityResults
import com.dev.gold.awesomelotto.utils.NavigationHandler
import com.dev.gold.awesomelotto.utils.TerminalOperators
import com.dev.gold.awesomelotto.utils.TerminalOperatorsImpl
import com.petarmarijanovic.rxactivityresult.RxActivityResult
import dagger.android.AndroidInjection
import io.reactivex.Single


abstract class BaseActivity<T : ViewDataBinding> :
    AppCompatActivity(),
    NavigationHandler,
    TerminalOperators by TerminalOperatorsImpl()
{

    protected lateinit var binding: T

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    override fun goTo(
        targetActivityClass: Class<*>,
        args: Bundle,
        requireResult: Boolean
    ): Single<ActivityResults> = if (requireResult) {
        RxActivityResult(this)
            .start(
                Intent(this, targetActivityClass)
                    .apply { putExtras(args) }
            )
            .map {
                ActivityResults(
                    it.resultCode == RESULT_OK,
                    it.data
                )
            }
    } else {
        Single.create { emitter ->

            startActivity(
                Intent(this, targetActivityClass)
                    .apply { putExtras(args) }
            )

            emitter.onSuccess(ActivityResults(true, Intent()))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun activityFinish() {
        this.finish()
    }
}
