package com.dev.gold.awesomelotto.utils

import io.reactivex.*
import io.reactivex.disposables.Disposable


interface TerminalOperators {

    fun <T : Disposable> T.add()

    fun <T> Observable<T>.subscribeAlter(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit = {}
    )

    fun <T> Observable<T>.subscribeAlter(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit = Logger::logException
    )

    fun <T> Observable<T>.subscribeAlter(
        onNext: (T) -> Unit
    )

    fun Completable.subscribeAlter(
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit = Logger::logException
    )

    fun Completable.subscribeAlter(
        onComplete: () -> Unit
    )

    fun <T> Single<T>.subscribeAlter(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = Logger::logException
    )

    fun <T> Single<T>.subscribeAlter(
        onSuccess: (T) -> Unit
    )

    fun <T> Maybe<T>.subscribeAlter(
        onSuccess: (T) -> Unit
    )

    fun <T> Maybe<T>.subscribeAlter(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = Logger::logException
    )

    fun <T> Flowable<T>.subscribeAlter(
        onSuccess: (T) -> Unit
    )

    fun <T> Flowable<T>.subscribeAlter(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = Logger::logException
    )

    fun clearObservers()
}
