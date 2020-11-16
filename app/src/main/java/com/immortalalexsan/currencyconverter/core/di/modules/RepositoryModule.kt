package com.immortalalexsan.currencyconverter.core.di.modules

import com.immortalalexsan.currencyconverter.data.repositories.ExchangeRatesRepository
import com.immortalalexsan.currencyconverter.domain.boundaries.ExchangeRatesBoundary
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun exchangeRatesRepository(repository: ExchangeRatesRepository): ExchangeRatesBoundary
}
