package com.immortalalexsan.currencyconverter.data.database.converters

import androidx.room.TypeConverter
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType

object CurrencyTypeConverter {

    @TypeConverter
    @JvmStatic
    fun currencyTypeToString(currencyType: CurrencyType?) = currencyType?.name

    @TypeConverter
    @JvmStatic
    fun stringToCurrencyType(text: String?) = text?.let { CurrencyType.valueOf(it) }
}
