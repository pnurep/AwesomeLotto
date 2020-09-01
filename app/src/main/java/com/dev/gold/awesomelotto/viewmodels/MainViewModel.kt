package com.dev.gold.awesomelotto.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.utils.UtilsClass.getLatestDrawNumber


class MainViewModel(
    winningRepository: WinningRepository
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
            ).subscribeAlter { winning ->

                _latestDrawNumber.value = winning.id

                _winningNumber.value = (winning.winningNumber.numbers)
                    .joinToString() + " + ${winning.bonusNumber}"

            }
    }
}
