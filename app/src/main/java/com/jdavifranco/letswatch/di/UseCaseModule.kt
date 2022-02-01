package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.domain.usecase.GetGenreListUC
import com.jdavifranco.letswatch.domain.usecase.GetMovieDetailsUC
import com.jdavifranco.letswatch.domain.usecase.GetMovieListByGenreUC
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMovieListByGenreUC(get()) }
    single { GetGenreListUC(get()) }
    single { GetMovieDetailsUC(get()) }
}