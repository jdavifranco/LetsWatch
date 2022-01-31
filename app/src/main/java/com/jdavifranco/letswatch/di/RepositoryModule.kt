package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.repository.MovieRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { MovieRepository(get())}
}