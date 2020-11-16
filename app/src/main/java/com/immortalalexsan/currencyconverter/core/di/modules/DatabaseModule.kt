package com.immortalalexsan.currencyconverter.core.di.modules

import android.content.Context
import com.immortalalexsan.currencyconverter.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule {

    @Singleton
    @Provides
    fun appDatabase(appContext: Context): AppDatabase = AppDatabase.getInstance(appContext)
}
