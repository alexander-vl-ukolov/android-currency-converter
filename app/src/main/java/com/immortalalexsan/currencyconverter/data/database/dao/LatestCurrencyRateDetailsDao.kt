package com.immortalalexsan.currencyconverter.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.immortalalexsan.currencyconverter.data.database.entities.LatestCurrencyRateDetailsEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface LatestCurrencyRateDetailsDao : BaseDao<LatestCurrencyRateDetailsEntity> {

    @Query("SELECT * FROM latest_currency_rate_details")
    override fun select(): Single<List<LatestCurrencyRateDetailsEntity>>

    @Query("DELETE FROM latest_currency_rate_details")
    override fun clear(): Completable
}
