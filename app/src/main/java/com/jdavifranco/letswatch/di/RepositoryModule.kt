package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.domain.data.MovieDataRepository
import com.jdavifranco.letswatch.repository.MovieRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<MovieDataRepository> { MovieRepository(get())}
}