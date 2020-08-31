package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.dao.LottoDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.repository.LottoRepository
import io.reactivex.Flowable
import io.reactivex.Single


class LottoRepositoryImpl(
    private val lottoDao: LottoDao
) : LottoRepository {

    override fun generateLotto(lotto: List<Lotto>): Single<List<Long>> =
        lottoDao.insert(lotto)

    override fun updateLotto(lotto: Lotto): Single<Int> =
        lottoDao.update(lotto)

    override fun updateAllLotto(lotto: List<Lotto>): Single<Int> =
        lottoDao.updateAll(lotto)

    override fun deleteAllLotto(): Single<Int> =
        lottoDao.deleteAll()

    override fun getAllLotto(): Flowable<List<Lotto>> =
        lottoDao.loadLotto()
}
