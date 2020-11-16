package com.immortalalexsan.currencyconverter.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.immortalalexsan.currencyconverter.data.database.converters.CurrencyRatesConverter
import com.immortalalexsan.currencyconverter.data.database.converters.CurrencyTypeConverter
import com.immortalalexsan.currencyconverter.data.database.converters.LocalDateConverter
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRates
import java.time.LocalDate

@Entity(tableName = "latest_currency_rate_details")
data class LatestCurrencyRateDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 1,

    @field:TypeConverters(CurrencyTypeConverter::class)
    @ColumnInfo(name = "base", typeAffinity = ColumnInfo.TEXT)
    val base: CurrencyType,

    @field:TypeConverters(LocalDateConverter::class)
    @ColumnInfo(name = "date", typeAffinity = ColumnInfo.TEXT)
    val date: LocalDate,

    @field:TypeConverters(CurrencyRatesConverter::class)
    @ColumnInfo(name = "rates", typeAffinity = ColumnInfo.BLOB)
    val rates: CurrencyRates
) : BaseEntity()
