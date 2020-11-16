package com.immortalalexsan.currencyconverter.core.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Singleton
    @Provides
    @Named("ProcessingScheduler")
    fun processingScheduler(): Scheduler = Schedulers.io()

    @Singleton
    @Provides
    @Named("PostProcessingScheduler")
    fun postProcessingScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
