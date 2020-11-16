package com.immortalalexsan.currencyconverter.presentation.entities

import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRates
import java.time.LocalDate

data class CurrencyRateDetailsLocal(
    val base: CurrencyType,
    val date: LocalDate,
    val rates: CurrencyRates
)
