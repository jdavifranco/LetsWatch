package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.datasource.repository.Repository
import org.koin.dsl.module


val repositoryModule = module {
    single { Repository(get() )}
}