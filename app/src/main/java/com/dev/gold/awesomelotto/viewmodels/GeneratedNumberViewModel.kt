package com.dev.gold.awesomelotto.viewmodels

import androidx.databinding.ObservableList
import com.dev.gold.awesomelotto.repository.LottoRepository
import com.dev.gold.awesomelotto.utils.toStringDate
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GeneratedNumberViewModel(
    private val listData: ObservableList<Any>,
    lottoRepository: LottoRepository
) : BaseViewModel() {

    init {
        lottoRepository.getAllUserGeneratedLotto()
            .subscribeOn(Schedulers.io())
            .flatMap { lottoList ->
                Flowable.create<Any>(
                    { emitter ->
                        lottoList.groupBy {
                            it.date.toStringDate("yyyy-MM-dd")
                        }.map { entry ->
                            emitter.onNext(entry.key)
                            entry.value.forEach { lotto ->
                                emitter.onNext(lotto)
                            }
                        }

                        emitter.onComplete()
                    }, BackpressureStrategy.BUFFER
                ).toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeAlter {
                listData.addAll(it)
            }
    }
}
