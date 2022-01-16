package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.datasource.local.LocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module{
    single { LocalDataSource.getInstance(androidContext())}
}