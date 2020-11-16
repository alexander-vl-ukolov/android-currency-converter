package com.immortalalexsan.currencyconverter.data.network.adapters

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRatesRange
import java.lang.reflect.Type
import java.time.LocalDate
import javax.inject.Inject

class CurrencyRatesRangeAdapter @Inject constructor() :
    JsonDeserializer<CurrencyRatesRange> {

    private val remoteCurrencyRatesAdapter = CurrencyRatesAdapter()

    private val gson = GsonBuilder()
        .setLenient()
        .excludeFieldsWithoutExposeAnnotation()
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        .create()

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyRatesRange {
        val currencyRatesRange = CurrencyRatesRange()
        json?.asJsonObject?.entrySet()?.forEach { rate ->
            if (rate.value.isJsonObject) {
                val currencyRates = remoteCurrencyRatesAdapter.deserialize(rate.value, null, null)
                val date = gson.fromJson(rate.key, LocalDate::class.java)
                currencyRatesRange[date] = currencyRates
            }
        }
        return currencyRatesRange
    }
}
