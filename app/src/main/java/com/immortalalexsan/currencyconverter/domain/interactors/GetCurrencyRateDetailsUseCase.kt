package com.immortalalexsan.currencyconverter.domain.interactors

import com.immortalalexsan.currencyconverter.domain.boundaries.ExchangeRatesBoundary
import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class GetCurrencyRateDetailsUseCase @Inject constructor(
    private val exchangeRatesRepository: ExchangeRatesBoundary,
    @Named("ProcessingScheduler") processingScheduler: Scheduler,
    @Named("PostProcessingScheduler") postProcessingScheduler: Scheduler
) : UseCase<CurrencyRateDetails, List<CurrencyType>>(processingScheduler, postProcessingScheduler) {

    override fun createSingle(params: List<CurrencyType>?): Single<CurrencyRateDetails> {
        if (params.isNullOrEmpty()) throw NullPointerException("Param \"symbols\" is null")
        return exchangeRatesRepository.getRates(params.joinToString(","))
    }
}
