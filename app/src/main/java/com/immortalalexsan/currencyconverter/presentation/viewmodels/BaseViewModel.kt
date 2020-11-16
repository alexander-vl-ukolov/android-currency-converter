package com.immortalalexsan.currencyconverter.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val compositeDisposable = CompositeDisposable()

    protected fun onSubscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
        _progress.value = true
    }

    protected fun onFinally() {
        _progress.value = false
    }

    protected fun onError(throwable: Throwable) {
        _error.value = throwable
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}
