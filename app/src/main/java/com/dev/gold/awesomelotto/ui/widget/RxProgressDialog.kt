package com.dev.gold.awesomelotto.ui.widget

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import androidx.annotation.StringRes
import com.dev.gold.awesomelotto.data.OnDialogCancel
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

    private fun <T> ObservableEmitter<T>.process(
        upstream: Observable<T>,
        dialog: ProgressDialog
    ) {
        if (isCancelable)
            dialog.setOnCancelListener { onError(OnDialogCancel) }

        dialog.setOnDismissListener { onComplete() }

        val disposable = upstream.subscribe(
            ::onNext,
            ::onError,
            ::onComplete
        )

        setDisposable(disposable)
    }

    private fun CompletableEmitter.process(
        upstream: Completable,
        dialog: ProgressDialog
    ) {
        if (isCancelable)
            dialog.setOnCancelListener { onError(OnDialogCancel) }

        dialog.setOnDismissListener { onComplete() }

        val disposable =
            upstream.subscribe(
                ::onComplete,
                ::onError
            )

        setDisposable(disposable)
    }

    private fun <T> FlowableEmitter<T>.process(
        upstream: Flowable<T>,
        dialog: ProgressDialog
    ) {
        if (isCancelable)
            dialog.setOnCancelListener { onError(OnDialogCancel) }

        dialog.setOnDismissListener { onComplete() }

        val disposable = upstream.subscribe(
            ::onNext,
            ::onError,
            ::onComplete
        )

        setDisposable(disposable)
    }

    private fun <T> SingleEmitter<T>.process(
        upstream: Single<T>,
        dialog: ProgressDialog
    ) {
        if (isCancelable)
            dialog.setOnCancelListener { onError(OnDialogCancel) }

        val disposable =
            upstream.subscribe(
                ::onSuccess,
                ::onError
            )

        setDisposable(disposable)
    }

    private fun <T> MaybeEmitter<T>.process(
        upstream: Maybe<T>,
        dialog: ProgressDialog
    ) {
        if (isCancelable)
            dialog.setOnCancelListener { onError(OnDialogCancel) }

        val disposable =
            upstream.subscribe(
                ::onSuccess,
                ::onError,
                ::onComplete
            )

        setDisposable(disposable)
    }

    fun <T> forObservable(source: Observable<T>): Observable<T> =
        Observable.using(
            this::makeDialog,
            { dialog ->
                Observable.create { emitter ->
                    emitter.process(source, dialog)
                }
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
                Flowable.create(
                    { emitter ->
                        emitter.process(source, dialog)
                    }, backPressureStrategy
                )
            },
            Dialog::dismiss
        )

    fun <T> forSingle(source: Single<T>): Single<T> = Single.using<T, ProgressDialog>(
        this::makeDialog,
        { dialog ->
            Single.create { emitter ->
                emitter.process(source, dialog)
            }
        },
        Dialog::dismiss
    )

    fun <T> forMaybe(source: Maybe<T>): Maybe<T> = Maybe.using<T, ProgressDialog>(
        this::makeDialog,
        { dialog ->
            Maybe.create { emitter ->
                emitter.process(source, dialog)
            }
        },
        Dialog::dismiss
    )

    fun forCompletable(source: Completable): Completable = Completable.using(
        this::makeDialog,
        { dialog ->
            Completable.create { emitter ->
                emitter.process(source, dialog)
            }
        },
        Dialog::dismiss
    )

    private fun makeDialog(): ProgressDialog =
        ProgressDialog.show(context, title, message, true, isCancelable)

    companion object {
        fun from(context: Context): RxProgressDialog = RxProgressDialog(context)
    }
}
