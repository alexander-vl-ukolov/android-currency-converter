package com.immortalalexsan.currencyconverter.core.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.goteddy.kidsagent.data.database.TestDatabase
import javax.inject.Singleton

@Module
class TestDatabaseModule : DatabaseModule() {

    @Singleton
    @Provides
    fun testDatabase(appContext: Context): TestDatabase = TestDatabase.getInstance(appContext)
}
