package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.network.MoviesService
import com.jdavifranco.letswatch.network.infokeys.MOVIES_BASE_URL
import com.jdavifranco.letswatch.repository.Repository
import com.jdavifranco.letswatch.ui.gallery.GalleryViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val repositoryModule = module {
    //retrofit Service
    single { retrofit().create(MoviesService::class.java) }
    //repository
    single {Repository(get())}
}


val appModule = module {
    //gallery ViewModel
    viewModel { GalleryViewModel(get()) }
}


/*
    Create moshi object para ser usado pelo retrofit como converter
    Moshi é uma biblioteca json que facilita a conversao de json para objetos java
    https://github.com/square/moshi
 */
private fun moshi() = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//criando o objeto retrofit que servirá para criar a api
private fun retrofit() = Retrofit.Builder()
    .baseUrl(MOVIES_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi()))
    .build()