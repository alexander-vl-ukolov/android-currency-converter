package com.immortalalexsan.currencyconverter.data.network.mappers

import com.immortalalexsan.currencyconverter.data.network.entities.CurrencyRateDetailsRemote
import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.mappers.Mapper
import javax.inject.Inject

class CurrencyRateDetailsRemoteMapper @Inject constructor() :
    Mapper<CurrencyRateDetailsRemote, CurrencyRateDetails> {

    override fun mapToInput(output: CurrencyRateDetails): CurrencyRateDetailsRemote {
        return with(output) { CurrencyRateDetailsRemote(base, date, rates) }
    }

    override fun mapToOutput(input: CurrencyRateDetailsRemote): CurrencyRateDetails {
        return with(input) { CurrencyRateDetails(base, date, rates) }
    }
}
