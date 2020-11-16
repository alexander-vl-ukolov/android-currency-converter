package com.immortalalexsan.currencyconverter.core.di.modules

import android.content.Context
import com.immortalalexsan.currencyconverter.core.platform.NetworkDetails
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlatformModule {

    @Singleton
    @Provides
    fun networkDetails(context: Context) = NetworkDetails(context)
}
