package com.dev.gold.awesomelotto.utils

import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class TerminalOperatorsImpl : TerminalOperators {

    private val compositeDisposable = CompositeDisposable()

    override fun <T : Disposable> T.add() {
        compositeDisposable.add(this)
    }

    override fun <T> Observable<T>.subscribeAlter(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit
    ) = subscribe(onNext, onError, onComplete)
        .add()

    override fun <T> Observable<T>.subscribeAlter(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribeAlter(onNext, onError, {})

    override fun <T> Observable<T>.subscribeAlter(
        onNext: (T) -> Unit
    ) = subscribeAlter(onNext, Logger::logException)

    override fun Completable.subscribeAlter(
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribe(onComplete, onError)
        .add()

    override fun Completable.subscribeAlter(
        onComplete: () -> Unit
    ) = subscribeAlter(onComplete, Logger::logException)

    override fun <T> Single<T>.subscribeAlter(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribe(onSuccess, onError)
        .add()

    override fun <T> Single<T>.subscribeAlter(
        onSuccess: (T) -> Unit
    ) = subscribeAlter(onSuccess, Logger::logException)

    override fun <T> Maybe<T>.subscribeAlter(
        onSuccess: (T) -> Unit
    ) = subscribeAlter(onSuccess, Logger::logException)

    override fun <T> Maybe<T>.subscribeAlter(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribe(onSuccess, onError).add()

    override fun <T> Flowable<T>.subscribeAlter(
        onSuccess: (T) -> Unit
    ) = subscribeAlter(onSuccess, Logger::logException)

    override fun <T> Flowable<T>.subscribeAlter(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribe(onSuccess, onError).add()

    override fun clearObservers() {
        compositeDisposable.clear()
    }
}
