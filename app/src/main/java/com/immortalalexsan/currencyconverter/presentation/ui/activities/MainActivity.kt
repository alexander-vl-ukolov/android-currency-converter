package com.immortalalexsan.currencyconverter.presentation.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.immortalalexsan.currencyconverter.R
import com.immortalalexsan.currencyconverter.core.extensions.error
import com.immortalalexsan.currencyconverter.core.extensions.getViewModel
import com.immortalalexsan.currencyconverter.core.extensions.progress
import com.immortalalexsan.currencyconverter.core.extensions.setAppearance
import com.immortalalexsan.currencyconverter.core.extensions.showMessage
import com.immortalalexsan.currencyconverter.core.extensions.success
import com.immortalalexsan.currencyconverter.data.cache.CacheKey
import com.immortalalexsan.currencyconverter.domain.entities.enums.CurrencyType
import com.immortalalexsan.currencyconverter.domain.exceptions.Failure
import com.immortalalexsan.currencyconverter.domain.exceptions.FailureException
import com.immortalalexsan.currencyconverter.presentation.entities.CurrencyRateDetailsLocal
import com.immortalalexsan.currencyconverter.presentation.viewmodels.MainViewModel
import com.immortalalexsan.currencyconverter.presentation.views.AsyncOperationView
import com.immortalalexsan.currencyconverter.presentation.views.MainView
import dagger.android.support.DaggerAppCompatActivity
import io.paperdb.Book
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_progress.*
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), AsyncOperationView, MainView,
    AdapterView.OnItemClickListener, View.OnClickListener {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    @Inject
    lateinit var cache: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeViewModel()
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val adapter = parent.adapter
        val currencyType = adapter.getItem(position) as CurrencyType
        when (adapter) {
            et_convert_from.adapter -> cacheCurrencyType(CacheKey.CURRENCY_TYPE_FROM, currencyType)
            et_convert_to.adapter -> cacheCurrencyType(CacheKey.CURRENCY_TYPE_TO, currencyType)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_convert) convertCurrency()
    }

    override fun onProgress(enabled: Boolean) {
        layout_progress.setAppearance(enabled)
    }

    override fun onError(throwable: Throwable) {
        when ((throwable as FailureException).failure) {
            is Failure.ConnectionError -> showMessage("Нет соединения с сервером")
            is Failure.ServerError -> showMessage("Внутренняя ошибка сервера")
            is Failure.IoError -> showMessage("Ошибка во время выполнения запроса")
        }
    }

    override fun onSuccess(currencyRateDetails: CurrencyRateDetailsLocal) {
        val currencyTypeFrom = extractCurrencyType(CacheKey.CURRENCY_TYPE_FROM)
        val currencyTypeTo = extractCurrencyType(CacheKey.CURRENCY_TYPE_TO)

        val exchangeRateFrom = currencyRateDetails.rates[currencyTypeFrom] ?: 1.0
        val exchangeRateTo = currencyRateDetails.rates[currencyTypeTo] ?: 1.0

        val course = exchangeRateTo / exchangeRateFrom
        val amountFrom = et_amount.text.toString().toInt()
        val amountTo = amountFrom * course

        val decimalFormat = DecimalFormat("#.####").apply { roundingMode = RoundingMode.CEILING }
        val result = """
            ${decimalFormat.format(amountTo)} $currencyTypeTo
            1 $currencyTypeFrom = ${decimalFormat.format(course)} $currencyTypeTo
        """.trimIndent()

        tv_result.text = result
    }

    private fun initComponents() {
        val currencies = enumValues<CurrencyType>()

        et_convert_from.setAdapter(createAdapter(currencies))
        et_convert_from.setText(extractCurrencyType(CacheKey.CURRENCY_TYPE_FROM).name, false)
        et_convert_from.onItemClickListener = this

        et_convert_to.setAdapter(createAdapter(currencies))
        et_convert_to.setText(extractCurrencyType(CacheKey.CURRENCY_TYPE_TO).name, false)
        et_convert_to.onItemClickListener = this

        btn_convert.setOnClickListener(this)
    }

    private fun createAdapter(currencies: Array<CurrencyType>) =
        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, currencies)

    private fun cacheCurrencyType(key: CacheKey, currencyType: CurrencyType) =
        cache.write(key.name, currencyType)

    private fun extractCurrencyType(key: CacheKey): CurrencyType =
        cache.read(key.name) ?: CurrencyType.EUR

    private fun convertCurrency() {
        if (!et_amount.text.isNullOrBlank()) {
            getCurrencyRateDetails()
        }
    }

    private fun initViewModel() {
        viewModel = getViewModel(vmFactory) {
            progress(progress, ::onProgress)
            error(error, ::onError)
            success(currencyRateDetails, ::onSuccess)
        }
    }

    private fun disposeViewModel() {
        viewModel.dispose()
    }

    private fun getCurrencyRateDetails() {
        viewModel.getCurrencyRateDetails()
    }
}
