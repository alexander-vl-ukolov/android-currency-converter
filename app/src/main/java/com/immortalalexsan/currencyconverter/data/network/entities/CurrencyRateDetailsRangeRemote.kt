package com.immortalalexsan.currencyconverter.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRatesRange
import java.time.LocalDate

data class CurrencyRateDetailsRangeRemote(
    @Expose @SerializedName("base") val base: CurrencyType,
    @Expose @SerializedName("start_at") val startDate: LocalDate,
    @Expose @SerializedName("end_at") val endDate: LocalDate,
    @Expose @SerializedName("rates") val rates: CurrencyRatesRange
)
