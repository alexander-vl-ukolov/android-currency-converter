package com.immortalalexsan.currencyconverter

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.immortalalexsan.currencyconverter.core.app.ThisApplication
import com.immortalalexsan.currencyconverter.core.di.components.DaggerTestAppComponent
import com.immortalalexsan.currencyconverter.data.database.mappers.LatestCurrencyRateDetailsEntityMapper
import com.immortalalexsan.currencyconverter.data.network.entities.CurrencyRateDetailsRemote
import com.immortalalexsan.currencyconverter.data.network.mappers.CurrencyRateDetailsRemoteMapper
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.goteddy.kidsagent.data.database.TestDatabase
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class DatabaseIsudTest {

    private val jsonString = """
        {"base"="EUR", "date"="2020-11-10", "rates"={"RUB"=90.2297, "CNY"=7.8107, "CHF"=1.0817, "USD"=1.1808, "GBP"=0.89183}}
    """.trimIndent()

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var remoteMapper: CurrencyRateDetailsRemoteMapper

    @Inject
    lateinit var entityMapper: LatestCurrencyRateDetailsEntityMapper

    @Inject
    lateinit var testDatabase: TestDatabase

    @Before
    fun setup() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as ThisApplication
        DaggerTestAppComponent.builder()
            .application(app)
            .build()
            .inject(this)
    }

    @Test
    fun latestCurrencyRateDetailsEntityISUDTest() {
        val dao = testDatabase.latestCurrencyRateDetailsDao

        assertThat(dao.select().blockingGet(), isEmpty)

        val entity = entityMapper.mapToInput(
            remoteMapper.mapToOutput(
                gson.fromJson(
                    jsonString,
                    CurrencyRateDetailsRemote::class.java
                )
            )
        )

        dao.insertIgnore(entity).blockingGet()
        dao.select().blockingGet().let { list ->
            assertThat(list, hasSize(equalTo(1)))
            assertThat(list.first(), equalTo(entity))
        }

        dao.delete(entity).blockingGet()
        assertThat(dao.select().blockingGet(), isEmpty)
    }
}
