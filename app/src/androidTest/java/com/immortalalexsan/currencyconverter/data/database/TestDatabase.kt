package ru.goteddy.kidsagent.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.immortalalexsan.currencyconverter.data.database.AppDatabase
import com.immortalalexsan.currencyconverter.data.database.entities.LatestCurrencyRateDetailsEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        LatestCurrencyRateDetailsEntity::class
    ]
)
abstract class TestDatabase : AppDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: TestDatabase? = null

        fun getInstance(context: Context): TestDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance =
                        Room.inMemoryDatabaseBuilder(context, TestDatabase::class.java)
                            .fallbackToDestructiveMigration()
                            .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}
