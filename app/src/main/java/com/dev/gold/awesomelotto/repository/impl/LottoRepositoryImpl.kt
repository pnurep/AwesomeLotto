package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.db.dao.LottoDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.repository.LottoRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LottoRepositoryImpl(
    private val lottoDao: LottoDao
) : LottoRepository {

    override fun generateLotto(lotto: Lotto): Long =
        lottoDao.insert(lotto)

    override fun updateLotto(lotto: Lotto): Single<Int> =
        lottoDao.update(lotto)

    override fun updateAllLotto(lotto: List<Lotto>): Single<Int> =
        lottoDao.updateAll(lotto)

    override fun deleteAllLotto(): Single<Int> =
        lottoDao.deleteAll()

    override fun getAllLotto(): Flowable<List<Lotto>> =
        lottoDao.loadLotto()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
