package com.dev.gold.awesomelotto.viewmodels

import androidx.databinding.ObservableList
import com.dev.gold.awesomelotto.repository.LottoRepository
import com.dev.gold.awesomelotto.utils.toStringDate
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


class GeneratedNumberViewModel(
    private val listData: ObservableList<Any>,
    lottoRepository: LottoRepository
) : BaseViewModel() {

    init {
        lottoRepository.getAllLotto()
            .flatMapSingle { lottoList ->
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
            .subscribeAlter {
                listData.addAll(it)
            }
    }
}
