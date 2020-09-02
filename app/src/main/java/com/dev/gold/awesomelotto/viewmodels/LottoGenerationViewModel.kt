package com.dev.gold.awesomelotto.viewmodels

import androidx.databinding.ObservableList
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.repository.LottoGenerationRepository


class LottoGenerationViewModel(
    private val data: ObservableList<Lotto>,
    private val repo: LottoGenerationRepository
) : BaseViewModel() {

    fun onClickGenerate() {

        repo.generateLottoNumber(5)
            .subscribeAlter({ lottoList ->
                data.clear()
                data.addAll(lottoList)
            }, { throwable ->
                throwable
            })
    }
}
