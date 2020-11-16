package com.immortalalexsan.currencyconverter

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.immortalalexsan.currencyconverter.core.app.ThisApplication
import com.immortalalexsan.currencyconverter.core.di.components.DaggerTestAppComponent
import com.immortalalexsan.currencyconverter.data.network.api.ExchangeRatesApiService
import com.immortalalexsan.currencyconverter.data.network.entities.CurrencyRateDetailsRemote
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class ExchangeRatesApiServiceTest {

    private val response = """
        {"base"="EUR", "date"="2020-11-10", "rates"={"RUB"=90.2297, "CNY"=7.8107, "CHF"=1.0817, "USD"=1.1808, "GBP"=0.89183}}
    """.trimIndent()

    @Inject
    lateinit var api: ExchangeRatesApiService

    @Inject
    lateinit var gson: Gson

    private fun toCurrencyRatesRemote(jsonString: String) =
        gson.fromJson(jsonString, CurrencyRateDetailsRemote::class.java)

    @Before
    fun setup() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as ThisApplication
        DaggerTestAppComponent.builder()
            .application(app)
            .build()
            .inject(this)
    }

    @Ignore("Because the response changes every day.")
    @Test
    fun getRates() {
        val currencies = enumValues<CurrencyType>().toMutableList()
        currencies.remove(CurrencyType.EUR)
        val params = currencies.joinToString(",")
        val currencyRates = toCurrencyRatesRemote(response)
        val currencyRatesRemote = api.getRates(params).blockingGet().body()
        assertThat(currencyRates, equalTo(currencyRatesRemote))
    }
}
