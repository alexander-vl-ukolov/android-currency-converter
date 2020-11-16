package com.immortalalexsan.currencyconverter.domain.boundaries

import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import io.reactivex.rxjava3.core.Single

interface ExchangeRatesBoundary {

    fun getRates(symbols: String): Single<CurrencyRateDetails>
}
