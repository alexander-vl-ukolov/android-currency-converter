package com.immortalalexsan.currencyconverter.core.runners

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.immortalalexsan.currencyconverter.core.app.ThisApplication

/**
 * Can be used in Gradle to override the application class.
 */
@Suppress("unused")
class InstrumentationTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, ThisApplication::class.java.name, context)
    }
}
