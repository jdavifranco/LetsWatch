package com.jdavifranco.letswatch

import android.app.Application
import com.jdavifranco.letswatch.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LetsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(applicationContext)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}