package com.immortalalexsan.currencyconverter.presentation.views

import com.immortalalexsan.currencyconverter.presentation.entities.CurrencyRateDetailsLocal

interface MainView {

    fun onSuccess(currencyRateDetails: CurrencyRateDetailsLocal)
}
