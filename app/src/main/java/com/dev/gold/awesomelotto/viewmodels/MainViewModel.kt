package com.dev.gold.awesomelotto.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.gold.awesomelotto.data.ActivityConstants.*
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.utils.NavigationHandler
import com.dev.gold.awesomelotto.utils.UtilsClass.getLatestDrawNumber


class MainViewModel(
    private val navigationHandler: NavigationHandler,
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

    fun goToQrCodeActivity() {
        navigationHandler.goTo(
            QRCODE.target
        ).subscribeAlter {}
    }

    fun goToPastWinningActivity() {
        navigationHandler.goTo(TODO())
    }
}
