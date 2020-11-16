package com.immortalalexsan.currencyconverter.data.repositories

import com.immortalalexsan.currencyconverter.core.platform.NetworkDetails
import com.immortalalexsan.currencyconverter.data.database.AppDatabase
import com.immortalalexsan.currencyconverter.data.database.mappers.LatestCurrencyRateDetailsEntityMapper
import com.immortalalexsan.currencyconverter.data.network.api.ExchangeRatesApiService
import com.immortalalexsan.currencyconverter.data.network.mappers.CurrencyRateDetailsRemoteMapper
import com.immortalalexsan.currencyconverter.domain.boundaries.ExchangeRatesBoundary
import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.exceptions.Failure
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ExchangeRatesRepository @Inject constructor(
    private val networkDetails: NetworkDetails,
    private val apiService: ExchangeRatesApiService,
    private val appDatabase: AppDatabase,
    private val remoteMapper: CurrencyRateDetailsRemoteMapper,
    private val entityMapper: LatestCurrencyRateDetailsEntityMapper
) : BaseRepository(), ExchangeRatesBoundary {

    override fun getRates(symbols: String): Single<CurrencyRateDetails> {
        return when (networkDetails.isNetworkAvailable()) {
            true -> getRatesFromServer(symbols)
            false -> getRatesFromDatabase()
        }
    }

    private fun getRatesFromServer(symbols: String): Single<CurrencyRateDetails> =
        request(apiService.getRates(symbols), remoteMapper::mapToOutput)
            .onErrorResumeNext { getRatesFromDatabase() }
            .doOnSuccess { saveRatesToDatabase(it) }

    private fun getRatesFromDatabase(): Single<CurrencyRateDetails> =
        appDatabase.latestCurrencyRateDetailsDao.select()
            .flatMap {
                when (it.isEmpty()) {
                    true -> error(Failure.ConnectionError)
                    false -> Single.just(entityMapper.mapToOutput(it.first()))
                }
            }

    private fun saveRatesToDatabase(details: CurrencyRateDetails) {
        val items = entityMapper.mapToInput(details)
        val dao = appDatabase.latestCurrencyRateDetailsDao
        appDatabase.runInTransaction {
            dao.clear().blockingAwait()
            dao.insertIgnore(items).blockingGet()
        }
    }
}
