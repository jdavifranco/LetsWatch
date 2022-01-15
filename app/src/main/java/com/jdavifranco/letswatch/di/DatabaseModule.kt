package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.datasource.local.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module{
    single { MovieDatabase.getInstance(androidContext()).movieDao}
}