package com.immortalalexsan.currencyconverter.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <L : LiveData<Boolean>> LifecycleOwner.progress(liveData: L, body: (Boolean) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Throwable>> LifecycleOwner.error(liveData: L, body: (Throwable) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> LifecycleOwner.success(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))