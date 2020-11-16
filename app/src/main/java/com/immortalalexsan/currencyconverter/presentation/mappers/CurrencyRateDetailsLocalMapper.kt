package com.immortalalexsan.currencyconverter.presentation.mappers

import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.mappers.Mapper
import com.immortalalexsan.currencyconverter.presentation.entities.CurrencyRateDetailsLocal
import javax.inject.Inject

class CurrencyRateDetailsLocalMapper @Inject constructor() :
    Mapper<CurrencyRateDetailsLocal, CurrencyRateDetails> {

    override fun mapToInput(output: CurrencyRateDetails): CurrencyRateDetailsLocal {
        return with(output) { CurrencyRateDetailsLocal(base, date, rates) }
    }

    override fun mapToOutput(input: CurrencyRateDetailsLocal): CurrencyRateDetails {
        return with(input) { CurrencyRateDetails(base, date, rates) }
    }
}
