package com.immortalalexsan.currencyconverter.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.immortalalexsan.currencyconverter.domain.entities.CurrencyRateDetails
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.interactors.GetCurrencyRateDetailsUseCase
import com.immortalalexsan.currencyconverter.presentation.entities.CurrencyRateDetailsLocal
import com.immortalalexsan.currencyconverter.presentation.mappers.CurrencyRateDetailsLocalMapper
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: GetCurrencyRateDetailsUseCase,
    private val localMapper: CurrencyRateDetailsLocalMapper,
    app: Application
) : BaseViewModel(app) {

    private val _currencyRateDetails = MutableLiveData<CurrencyRateDetailsLocal>()
    val currencyRateDetails: LiveData<CurrencyRateDetailsLocal> = _currencyRateDetails

    private fun onSuccess(currencyRateDetails: CurrencyRateDetails) {
        _currencyRateDetails.value = localMapper.mapToInput(currencyRateDetails)
    }

    fun getCurrencyRateDetails() {
        val params = enumValues<CurrencyType>().toMutableList().apply { remove(CurrencyType.EUR) }
        useCase(params, ::onSubscribe, ::onFinally, ::onSuccess, ::onError)
    }
}
