package com.immortalalexsan.currencyconverter.core.di.components

import android.app.Application
import com.immortalalexsan.currencyconverter.core.app.ThisApplication
import com.immortalalexsan.currencyconverter.core.di.modules.ActivityContributorModule
import com.immortalalexsan.currencyconverter.core.di.modules.ApiServiceModule
import com.immortalalexsan.currencyconverter.core.di.modules.AppModule
import com.immortalalexsan.currencyconverter.core.di.modules.CacheModule
import com.immortalalexsan.currencyconverter.core.di.modules.DatabaseModule
import com.immortalalexsan.currencyconverter.core.di.modules.MapperModule
import com.immortalalexsan.currencyconverter.core.di.modules.PlatformModule
import com.immortalalexsan.currencyconverter.core.di.modules.RepositoryModule
import com.immortalalexsan.currencyconverter.core.di.modules.SchedulerModule
import com.immortalalexsan.currencyconverter.core.di.modules.UseCaseModule
import com.immortalalexsan.currencyconverter.core.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ApiServiceModule::class,
        MapperModule::class,
        RepositoryModule::class,
        ActivityContributorModule::class,
        ViewModelFactoryModule::class,
        UseCaseModule::class,
        SchedulerModule::class,
        PlatformModule::class,
        DatabaseModule::class,
        CacheModule::class
    ]
)
interface AppComponent : AndroidInjector<ThisApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}
