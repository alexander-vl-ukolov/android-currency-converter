package com.immortalalexsan.currencyconverter.core.extensions

import android.view.View

fun View.setAppearance(appearance: Boolean) {
    this.visibility = when (appearance) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}
