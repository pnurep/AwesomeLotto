package com.dev.gold.awesomelotto.repository

import com.dev.gold.awesomelotto.data.dto.Lotto
import io.reactivex.Single


interface LottoGenerationRepository {

    fun generateLottoNumber(
        count: Int
    ): Single<List<Lotto>>
}
