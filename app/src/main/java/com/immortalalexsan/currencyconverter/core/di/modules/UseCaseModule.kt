package com.immortalalexsan.currencyconverter.core.di.modules

import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.interactors.GetCurrencyRateDetailsUseCase
import com.immortalalexsan.currencyconverter.domain.interactors.UseCase
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun getCurrencyRateDetailsUseCase(useCase: GetCurrencyRateDetailsUseCase):
            UseCase<CurrencyRateDetails, Nothing>
}
