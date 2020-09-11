package com.dev.gold.awesomelotto.viewmodels

import androidx.lifecycle.ViewModel
import com.dev.gold.awesomelotto.utils.TerminalOperators
import com.dev.gold.awesomelotto.utils.TerminalOperatorsImpl


abstract class BaseViewModel(
    terminalOperators: TerminalOperators = TerminalOperatorsImpl()
) : ViewModel(),
    TerminalOperators by terminalOperators {

    open fun onResume() {

    }

    open fun onPause() {

    }

    public override fun onCleared() {
        clearObservers()
        super.onCleared()
    }
}
