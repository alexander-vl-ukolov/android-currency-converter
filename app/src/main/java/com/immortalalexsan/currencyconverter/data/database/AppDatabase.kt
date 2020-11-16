package com.immortalalexsan.currencyconverter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.immortalalexsan.currencyconverter.BuildConfig
import com.immortalalexsan.currencyconverter.data.database.dao.LatestCurrencyRateDetailsDao
import com.immortalalexsan.currencyconverter.data.database.entities.LatestCurrencyRateDetailsEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        LatestCurrencyRateDetailsEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract val latestCurrencyRateDetailsDao: LatestCurrencyRateDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}
