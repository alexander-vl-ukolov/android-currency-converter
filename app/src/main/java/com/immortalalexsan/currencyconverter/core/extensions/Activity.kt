package com.immortalalexsan.currencyconverter.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(
    vmFactory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val viewModel = ViewModelProvider(this, vmFactory)[T::class.java]
    viewModel.body()
    return viewModel
}
