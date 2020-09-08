package com.dev.gold.awesomelotto.repository.impl

import com.dev.gold.awesomelotto.data.db.dao.LottoDao
import com.dev.gold.awesomelotto.data.dto.Lotto
import com.dev.gold.awesomelotto.repository.LottoRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LottoRepositoryImpl(
    private val lottoDao: LottoDao
) : LottoRepository {

    override fun generateLotto(lotto: Lotto) =
        lottoDao.insert(lotto)

    override fun updateLotto(lotto: Lotto) =
        lottoDao.update(lotto)

    override fun updateAllLotto(lotto: List<Lotto>) =
        lottoDao.updateAll(lotto)

    override fun deleteAllLotto() =
        lottoDao.deleteAll()

    override fun getAllUserGeneratedLotto() =
        lottoDao.getAllUserGeneratedLotto()

    override fun getAllSavedLotto(): Flowable<List<Lotto>> =
        lottoDao.loadLotto()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
