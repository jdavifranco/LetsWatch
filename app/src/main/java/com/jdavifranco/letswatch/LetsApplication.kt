package com.jdavifranco.letswatch

import android.app.Application
import com.jdavifranco.letswatch.di.appModule
import com.jdavifranco.letswatch.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LetsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(applicationContext)
            modules(repositoryModule,appModule)
        }
    }
}