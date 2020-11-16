package com.immortalalexsan.currencyconverter.data.database.mappers

import com.immortalalexsan.currencyconverter.data.database.entities.LatestCurrencyRateDetailsEntity
import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.mappers.Mapper
import javax.inject.Inject

class LatestCurrencyRateDetailsEntityMapper @Inject constructor() :
    Mapper<LatestCurrencyRateDetailsEntity, CurrencyRateDetails> {

    override fun mapToInput(output: CurrencyRateDetails): LatestCurrencyRateDetailsEntity {
        return with(output) {
            LatestCurrencyRateDetailsEntity(base = base, date = date, rates = rates)
        }
    }

    override fun mapToOutput(input: LatestCurrencyRateDetailsEntity): CurrencyRateDetails {
        return with(input) { CurrencyRateDetails(base, date, rates) }
    }
}
