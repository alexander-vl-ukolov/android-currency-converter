package com.immortalalexsan.currencyconverter.core.di.modules

import com.immortalalexsan.currencyconverter.data.database.entities.LatestCurrencyRateDetailsEntity
import com.immortalalexsan.currencyconverter.data.database.mappers.LatestCurrencyRateDetailsEntityMapper
import com.immortalalexsan.currencyconverter.data.network.entities.CurrencyRateDetailsRemote
import com.immortalalexsan.currencyconverter.data.network.mappers.CurrencyRateDetailsRemoteMapper
import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.mappers.Mapper
import com.immortalalexsan.currencyconverter.presentation.entities.CurrencyRateDetailsLocal
import com.immortalalexsan.currencyconverter.presentation.mappers.CurrencyRateDetailsLocalMapper
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @Binds
    fun currencyRateDetailsRemoteMapper(mapper: CurrencyRateDetailsRemoteMapper):
            Mapper<CurrencyRateDetailsRemote, CurrencyRateDetails>

    @Binds
    fun latestCurrencyRateDetailsEntityMapper(mapper: LatestCurrencyRateDetailsEntityMapper):
            Mapper<LatestCurrencyRateDetailsEntity, CurrencyRateDetails>

    @Binds
    fun currencyRateDetailsLocalMapper(mapper: CurrencyRateDetailsLocalMapper):
            Mapper<CurrencyRateDetailsLocal, CurrencyRateDetails>
}
