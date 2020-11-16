package com.immortalalexsan.currencyconverter.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRates
import java.time.LocalDate

data class CurrencyRateDetailsRemote(
    @Expose @SerializedName("base") val base: CurrencyType,
    @Expose @SerializedName("date") val date: LocalDate,
    @Expose @SerializedName("rates") val rates: CurrencyRates
)
