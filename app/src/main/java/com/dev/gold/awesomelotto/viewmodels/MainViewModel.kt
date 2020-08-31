package com.dev.gold.awesomelotto.viewmodels

import com.dev.gold.awesomelotto.repository.WinningRepository


class MainViewModel(
    private val winningRepository: WinningRepository
) : BaseViewModel() {

    init {

        winningRepository

    }

}
