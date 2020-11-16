package com.immortalalexsan.currencyconverter.data.network.api

import com.immortalalexsan.currencyconverter.data.network.entities.CurrencyRateDetailsRemote
import com.immortalalexsan.currencyconverter.data.network.entities.CurrencyRateDetailsRangeRemote
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface ExchangeRatesApiService {

    /**
     * Get the latest foreign exchange reference rates.
     */
    @GET("/latest")
    fun getRates(): Single<Response<CurrencyRateDetailsRemote>>

    /**
     * Get historical rates for any day since 1999.
     *
     * @param date The date (yyyy-MM-dd) from which you want to know the exchange rates.
     */
    @GET("/{date}")
    fun getRates(@Path("date", encoded = true) date: LocalDate):
            Single<Response<CurrencyRateDetailsRemote>>

    /**
     * Rates are quoted against the Euro by default. Quote against a different currency
     * by setting the base parameter in your request.
     *
     * @param base Base currency to get exchange rates.
     */
    @GET("/latest")
    fun getRates(@Query("base", encoded = true) base: CurrencyType):
            Single<Response<CurrencyRateDetailsRemote>>

    /**
     * Request specific exchange rates by setting the symbols parameter.
     *
     * @param symbols Currencies for obtaining exchange rates. For example, "RUB,USD"...
     */
    @GET("/latest")
    fun getRates(@Query("symbols", encoded = true) symbols: String):
            Single<Response<CurrencyRateDetailsRemote>>

    /**
     * Get historical rates for a time period.
     *
     * @param startDate The date (yyyy-MM-dd) from which you want to know the exchange rates.
     * @param startDate The date (yyyy-MM-dd) until which you want to know the exchange rates.
     */
    @GET("/history")
    fun getRates(
        @Query("start_at", encoded = true) startDate: LocalDate,
        @Query("end_at", encoded = true) endDate: LocalDate
    ): Single<Response<CurrencyRateDetailsRangeRemote>>

    /**
     * Limit results to specific exchange rates to save bandwidth with the symbols parameter.
     *
     * @param startDate The date (yyyy-MM-dd) from which you want to know the exchange rates.
     * @param startDate The date (yyyy-MM-dd) until which you want to know the exchange rates.
     * @param symbols Currencies for obtaining exchange rates. For example, "RUB,USD"...
     */
    @GET("/history")
    fun getRates(
        @Query("start_at", encoded = true) startDate: LocalDate,
        @Query("end_at", encoded = true) endDate: LocalDate,
        @Query("symbols", encoded = true) symbols: String
    ): Single<Response<CurrencyRateDetailsRangeRemote>>

    /**
     * Quote the historical rates against a different currency.
     *
     * @param startDate The date (yyyy-MM-dd) from which you want to know the exchange rates.
     * @param startDate The date (yyyy-MM-dd) until which you want to know the exchange rates.
     * @param base Base currency to get exchange rates.
     */
    @GET("/history")
    fun getRates(
        @Query("start_at", encoded = true) startDate: LocalDate,
        @Query("end_at", encoded = true) endDate: LocalDate,
        @Query("base", encoded = true) base: CurrencyType
    ): Single<Response<CurrencyRateDetailsRangeRemote>>
}
