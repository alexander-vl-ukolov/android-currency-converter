package com.immortalalexsan.currencyconverter.core.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.immortalalexsan.currencyconverter.BuildConfig
import com.immortalalexsan.currencyconverter.R
import com.immortalalexsan.currencyconverter.data.network.adapters.CurrencyRatesAdapter
import com.immortalalexsan.currencyconverter.data.network.adapters.CurrencyRatesRangeAdapter
import com.immortalalexsan.currencyconverter.data.network.adapters.LocalDateAdapter
import com.immortalalexsan.currencyconverter.data.network.api.ExchangeRatesApiService
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRates
import com.immortalalexsan.currencyconverter.domain.entities.support.CurrencyRatesRange
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedInputStream
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.time.LocalDate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Module
class ApiServiceModule {

    @Singleton
    @Provides
    fun trustManager(appContext: Context): X509TrustManager {
        val caFactory = CertificateFactory.getInstance("X.509")
        val caRes = R.raw.api_exchangeratesapi_io

        var ca: Certificate?
        BufferedInputStream(appContext.resources.openRawResource(caRes)).use {
            ca = caFactory.generateCertificate(it)
        }

        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType()).apply {
            load(null, null)
            setCertificateEntry("ca", ca)
        }

        val algorithm = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory = TrustManagerFactory.getInstance(algorithm).apply {
            init(keyStore)
        }

        return trustManagerFactory.trustManagers[0] as X509TrustManager
    }

    @Singleton
    @Provides
    fun sslSocketFactory(trustManager: X509TrustManager): SSLSocketFactory =
        SSLContext.getInstance("TLS")
            .apply { init(null, arrayOf(trustManager), null) }
            .socketFactory

    @Singleton
    @Provides
    fun certificatePinner(): CertificatePinner {
        val regex = "https?://|/.*".toRegex()
        val hostname = BuildConfig.BASE_URL.replace(regex, "")
        return CertificatePinner.Builder()
            .add(hostname, BuildConfig.PIN_SHA256)
            .build()
    }

    @Singleton
    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun client(
        sslSocketFactory: SSLSocketFactory,
        trustManager: X509TrustManager,
        certificatePinner: CertificatePinner,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(false)
        .hostnameVerifier { _, _ -> true }
        .certificatePinner(certificatePinner)
        .sslSocketFactory(sslSocketFactory, trustManager)
        .apply { if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor) }
        .build()

    @Singleton
    @Provides
    fun gson(
        localDateAdapter: LocalDateAdapter,
        currencyRatesAdapter: CurrencyRatesAdapter,
        currencyRatesRangeAdapter: CurrencyRatesRangeAdapter
    ): Gson = GsonBuilder()
        .setLenient()
        .excludeFieldsWithoutExposeAnnotation()
        .registerTypeAdapter(LocalDate::class.java, localDateAdapter)
        .registerTypeAdapter(CurrencyRates::class.java, currencyRatesAdapter)
        .registerTypeAdapter(CurrencyRatesRange::class.java, currencyRatesRangeAdapter)
        .create()

    @Singleton
    @Provides
    fun authRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @Singleton
    @Provides
    fun exchangeRatesApiService(retrofit: Retrofit): ExchangeRatesApiService =
        retrofit.create(ExchangeRatesApiService::class.java)
}
