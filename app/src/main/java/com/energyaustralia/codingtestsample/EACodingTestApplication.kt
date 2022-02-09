package com.energyaustralia.codingtestsample


import android.app.Application
import com.energyaustralia.eatest.di.appModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext


class EACodingTestApplication : Application() {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    override fun onCreate() {
        super.onCreate()
        // Required initialization logic here!
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@EACodingTestApplication)
            // declare modules
            modules(appModule)
        }
    }
}
