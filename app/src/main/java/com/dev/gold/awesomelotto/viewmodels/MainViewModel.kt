package com.dev.gold.awesomelotto.viewmodels

import android.Manifest
import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.gold.awesomelotto.data.ActivityConstants.*
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.utils.NavigationHandler
import com.dev.gold.awesomelotto.utils.PermissionManager
import com.dev.gold.awesomelotto.utils.UtilsClass.getLatestDrawNumber
import com.dev.gold.awesomelotto.utils.getActivity
import com.google.zxing.integration.android.IntentIntegrator
import io.reactivex.Observable


class MainViewModel(
    private val navigationHandler: NavigationHandler,
    private val permissionManager: PermissionManager,
    winningRepository: WinningRepository,
) : BaseViewModel() {

    private val _latestDrawNumber = MutableLiveData<Int>()
    val latestDrawNumber: LiveData<Int>
        get() = _latestDrawNumber

    private val _winningNumber = MutableLiveData<String>()
    val winningNumber: LiveData<String>
        get() = _winningNumber

    init {
        winningRepository
            .getWinningByDrawNumber(
                getLatestDrawNumber()
            ).subscribeAlter { (lotto, winning) ->
                _latestDrawNumber.value = winning.id

                _winningNumber.value = (lotto.numbers)
                    .joinToString() + " + ${winning.bonusNumber}"
            }
    }

    fun goToGenerateActivity() {
        navigationHandler.goTo(
            GENERATION.target
        ).subscribeAlter {}
    }

    fun goToGeneratedNumberActivity() {
        navigationHandler.goTo(
            GENERATED.target
        ).subscribeAlter {}
    }

    fun goToQrCodeActivity(v: View) {

        Observable.just(
            permissionManager.checkPermission(Manifest.permission.CAMERA)
        ).flatMap { granted ->
            if (!granted) {
                permissionManager.requestPermission(
                    v.getActivity(),
                    Manifest.permission.CAMERA
                )
            } else {
                Observable.just(true)
            }
        }
            .filter { it }
            .take(1)
            .singleElement()
            .flatMapSingle {
                navigationHandler.goTo(
                    targetActivityClass = QRCODE.target,
                    requireResult = true
                )
            }
            .filter { it.success }
            .subscribeAlter {

                IntentIntegrator.parseActivityResult(
                    Activity.RESULT_OK,
                    it.resultIntent
                )?.let { result ->
                    result.contents?.let { contents ->
                        Toast.makeText(v.context, "Scanned: $contents", Toast.LENGTH_SHORT).show()
                    } ?: run {
                        Toast.makeText(v.context, "Cancelled", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    fun goToPastWinningActivity() {
        navigationHandler.goTo(TODO())
    }
}
