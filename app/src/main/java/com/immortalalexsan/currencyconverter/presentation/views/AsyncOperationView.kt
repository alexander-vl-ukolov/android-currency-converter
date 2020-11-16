package com.immortalalexsan.currencyconverter.presentation.views

interface AsyncOperationView {

    fun onProgress(enabled: Boolean) {
    }

    fun onError(throwable: Throwable) {
    }
}
