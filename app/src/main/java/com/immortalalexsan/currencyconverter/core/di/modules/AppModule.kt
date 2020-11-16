package com.immortalalexsan.currencyconverter.core.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun appContext(app: Application): Context
}
