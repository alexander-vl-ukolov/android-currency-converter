package com.immortalalexsan.currencyconverter.domain.entities.support

import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import java.time.LocalDate

class CurrencyRatesRange : HashMap<LocalDate, Map<CurrencyType, Double>>()
