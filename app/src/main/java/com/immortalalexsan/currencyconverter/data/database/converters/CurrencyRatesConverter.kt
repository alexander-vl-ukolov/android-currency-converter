package com.immortalalexsan.currencyconverter.data.database.converters

import androidx.room.TypeConverter
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRates
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object CurrencyRatesConverter {

    @TypeConverter
    @JvmStatic
    fun currencyRatesToBlob(currencyRates: CurrencyRates?) = currencyRates?.let {
        ByteArrayOutputStream().use { bos ->
            ObjectOutputStream(bos).use { oos ->
                oos.writeObject(it)
                bos.toByteArray()
            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun blobToCurrencyRates(byteArray: ByteArray?) = byteArray?.let {
        ByteArrayInputStream(it).use { bis ->
            ObjectInputStream(bis).use { ois ->
                ois.readObject() as CurrencyRates
            }
        }
    }
}
