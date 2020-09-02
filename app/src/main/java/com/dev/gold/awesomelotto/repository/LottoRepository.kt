package com.dev.gold.awesomelotto.repository

import com.dev.gold.awesomelotto.data.dto.Lotto
import io.reactivex.Flowable
import io.reactivex.Single


interface LottoRepository{

    fun generateLotto(
        lotto: Lotto
    ): Long

    fun updateLotto(
        lotto: Lotto
    ): Single<Int>

    fun updateAllLotto(
        lotto: List<Lotto>
    ): Single<Int>

    fun deleteAllLotto(): Single<Int>

    fun getAllLotto(): Flowable<List<Lotto>>
}
