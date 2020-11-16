package com.immortalalexsan.currencyconverter.domain.interactors

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class UseCase<T, Params>(
    private val processingScheduler: Scheduler,
    private val postProcessingScheduler: Scheduler
) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun createSingle(params: Params? = null): Single<T>

    operator fun invoke(
        params: Params? = null,
        onSubscribe: (Disposable) -> Unit,
        onFinally: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        compositeDisposable.add(
            createSingle(params)
                .subscribeOn(processingScheduler)
                .observeOn(postProcessingScheduler)
                .doOnSubscribe(onSubscribe)
                .doFinally(onFinally)
                .subscribe(onSuccess, onError)
        )
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}
