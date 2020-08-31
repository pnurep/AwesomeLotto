package com.dev.gold.awesomelotto.viewmodels

import com.dev.gold.awesomelotto.repository.WinningRepository


class MainViewModel(
    winningRepository: WinningRepository
) : BaseViewModel() {

    init {

        winningRepository
            .getWinningByDrawNumber(926)
            .subscribeAlter({
                it
            }, { throwable ->
                throwable
            })
    }
}
