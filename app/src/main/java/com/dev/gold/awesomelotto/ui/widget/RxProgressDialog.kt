package com.dev.gold.awesomelotto.ui.widget

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import androidx.annotation.StringRes
import io.reactivex.*


class RxProgressDialog private constructor(
    val context: Context
) {

    private var title: String? = null

    private var message: String? = null

    private var isCancelable: Boolean = false

    private var indeterminate: Boolean = false

    fun setTitle(title: String) =
        apply { this.title = title }

    fun setTitle(@StringRes resId: Int) =
        apply { this.title = context.getString(resId) }

    fun setMessage(message: String) =
        apply { this.message = message }

    fun setMessage(@StringRes resId: Int) =
        apply { this.message = context.getString(resId) }

    fun setIsCancelable(isCancelable: Boolean) =
        apply { this.isCancelable = isCancelable }

    fun setIndeterminate(indeterminate: Boolean) =
        apply { this.indeterminate = indeterminate }

    private fun <T> process(
        upstream: Observable<T>,
        dialog: ProgressDialog
    ): Observable<T> =
        Observable.create { emitter ->

            if (isCancelable)
                dialog.setOnCancelListener { emitter.onComplete() }

            dialog.setOnDismissListener { emitter.onComplete() }

            val disposable = upstream.subscribe(
                emitter::onNext,
                emitter::onError,
                emitter::onComplete
            )
            emitter.setDisposable(disposable)
        }

    private fun process(
        upstream: Completable,
        dialog: ProgressDialog
    ): Completable =
        Completable.create { emitter ->

            if (isCancelable)
                dialog.setOnCancelListener { emitter.onComplete() }

            dialog.setOnDismissListener { emitter.onComplete() }

            val disposable =
                upstream.subscribe(
                    emitter::onComplete,
                    emitter::onError
                )
            emitter.setDisposable(disposable)
        }

    private fun <T> process(
        upstream: Flowable<T>,
        backpressureStrategy: BackpressureStrategy,
        dialog: ProgressDialog
    ): Flowable<T> = Flowable.create({ emitter ->

        if (isCancelable)
            dialog.setOnCancelListener { emitter.onComplete() }

        dialog.setOnDismissListener { emitter.onComplete() }

        val disposable = upstream.subscribe(
            emitter::onNext,
            emitter::onError,
            emitter::onComplete
        )
        emitter.setDisposable(disposable)
    }, backpressureStrategy)

    fun <T> forObservable(source: Observable<T>): Observable<T> =
        Observable.using(
            this::makeDialog,
            { dialog ->
                process(source, dialog)
            },
            Dialog::dismiss
        )

    fun <T> forFlowable(
        source: Flowable<T>,
        backPressureStrategy: BackpressureStrategy = BackpressureStrategy.BUFFER
    ): Flowable<T> =
        Flowable.using(
            this::makeDialog,
            { dialog ->
                process(source, backPressureStrategy, dialog)
            },
            Dialog::dismiss
        )

    fun <T> forSingle(source: Single<T>): Single<T> = Single.using<T, ProgressDialog>(
        this::makeDialog,
        {
            Single.create { emitter ->
                source.subscribe(emitter::onSuccess, emitter::onError)
            }
        },
        Dialog::dismiss
    )

    fun <T> forMaybe(source: Maybe<T>): Maybe<T> = Maybe.using<T, ProgressDialog>(
        this::makeDialog,
        {
            Maybe.create { emitter ->
                source.subscribe(
                    emitter::onSuccess,
                    emitter::onError,
                    emitter::onComplete
                )
            }
        },
        Dialog::dismiss
    )

    fun forCompletable(source: Completable): Completable = Completable.using(
        this::makeDialog,
        { dialog ->
            process(source, dialog)
        },
        Dialog::dismiss
    )

    private fun makeDialog(): ProgressDialog =
        ProgressDialog.show(context, title, message, true, isCancelable)

    companion object {
        fun from(context: Context): RxProgressDialog = RxProgressDialog(context)
    }
}
