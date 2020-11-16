package com.immortalalexsan.currencyconverter.core.di.modules

import dagger.Module
import dagger.Provides
import io.paperdb.Book
import io.paperdb.Paper
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun cache(): Book = Paper.book()
}
