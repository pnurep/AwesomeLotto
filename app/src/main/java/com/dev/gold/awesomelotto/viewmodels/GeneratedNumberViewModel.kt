package com.dev.gold.awesomelotto.viewmodels

import androidx.databinding.ObservableList
import com.dev.gold.awesomelotto.repository.LottoRepository
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
                            it.date
                        }.map { entry ->
                            emitter.onNext(entry.key.toString())
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
