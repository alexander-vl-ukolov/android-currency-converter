package com.immortalalexsan.currencyconverter.data.network.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRates
import java.lang.reflect.Type
import javax.inject.Inject

class CurrencyRatesAdapter @Inject constructor() : JsonDeserializer<CurrencyRates> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyRates {
        val currencyRates = CurrencyRates()
        json?.asJsonObject?.entrySet()?.forEach {
            val name = it.key
            if (enumContains<CurrencyType>(name)) {
                val currencyType = CurrencyType.valueOf(name)
                val exchangeRate = it.value.asDouble
                currencyRates[currencyType] = exchangeRate
            }
        }
        return currencyRates
    }

    private inline fun <reified T : Enum<T>> enumContains(name: String) =
        enumValues<T>().any { it.name == name }
}
