package com.immortalalexsan.currencyconverter.core.di.modules

import androidx.lifecycle.ViewModelProvider
import com.immortalalexsan.currencyconverter.presentation.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory
}
