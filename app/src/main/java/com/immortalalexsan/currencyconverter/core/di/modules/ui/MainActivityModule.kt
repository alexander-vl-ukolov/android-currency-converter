package com.immortalalexsan.currencyconverter.core.di.modules.ui

import androidx.lifecycle.ViewModel
import com.immortalalexsan.currencyconverter.core.di.annotations.ViewModelKey
import com.immortalalexsan.currencyconverter.presentation.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel
}
