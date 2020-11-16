package com.immortalalexsan.currencyconverter.data.network.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class LocalDateAdapter @Inject constructor() : JsonSerializer<LocalDate>,
    JsonDeserializer<LocalDate> {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun serialize(
        src: LocalDate,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.format(formatter))
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDate {
        val dateTimeString = json.asJsonPrimitive.asString
        return LocalDate.parse(dateTimeString, formatter)
    }
}
