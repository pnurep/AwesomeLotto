package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.db.dao.LottoDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.repository.LottoGenerationRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*


class LottoGenerationRepositoryImpl(
    private val lottoDao: LottoDao
) : LottoGenerationRepository {

    override fun generateLottoNumber(
        count: Int
    ): Single<List<Lotto>> =
        Observable.range(1, count)
            .subscribeOn(Schedulers.io())
            .map {
                generateLotto()
            }
            .map { lotto ->
                lottoDao.insert(lotto)
            }
            .toList()
            .flatMap { ids ->
                lottoDao.loadLottoByIds(ids.map { it.toInt() })
            }
            .observeOn(AndroidSchedulers.mainThread())

    private fun generateLotto() =
        Lotto().apply {
            numbers = (1 until 46)
                .toMutableList()
                .apply { shuffle() }
                .take(6)
                .sorted()
            
            date = Date()
        }
}
