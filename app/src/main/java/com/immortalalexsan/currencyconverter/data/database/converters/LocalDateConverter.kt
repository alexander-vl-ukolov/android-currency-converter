package com.immortalalexsan.currencyconverter.data.database.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateConverter {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @TypeConverter
    @JvmStatic
    fun localDateToString(date: LocalDate?) = date?.format(formatter)

    @TypeConverter
    @JvmStatic
    fun stringToLocalDate(text: String?) = text?.let { LocalDate.parse(it, formatter) }
}
