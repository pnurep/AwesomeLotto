package com.dev.gold.awesomelotto.viewmodels

import com.dev.gold.awesomelotto.repository.WinningRepository


class PastWinningViewModel(
    private val winningRepository: WinningRepository
) : BaseViewModel() {

    init {

        winningRepository
            .getAllWinnings()

    }

}
