package com.immortalalexsan.currencyconverter.domain.entities

import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRates
import java.time.LocalDate

data class CurrencyRateDetails(
    val base: CurrencyType,
    val date: LocalDate,
    val rates: CurrencyRates
)
