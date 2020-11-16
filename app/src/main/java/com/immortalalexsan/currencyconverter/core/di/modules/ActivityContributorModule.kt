package com.immortalalexsan.currencyconverter.core.di.modules

import com.immortalalexsan.currencyconverter.core.di.modules.ui.MainActivityModule
import com.immortalalexsan.currencyconverter.presentation.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityContributorModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity
}
