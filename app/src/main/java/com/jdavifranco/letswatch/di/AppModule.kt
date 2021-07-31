package com.jdavifranco.letswatch.di

import androidx.room.Room
import com.jdavifranco.letswatch.database.MovieDatabase
import com.jdavifranco.letswatch.network.MoviesService
import com.jdavifranco.letswatch.network.infokeys.MOVIES_BASE_URL
import com.jdavifranco.letswatch.repository.Repository
import com.jdavifranco.letswatch.ui.gallery.GalleryViewModel
import com.jdavifranco.letswatch.ui.mainactivity.MainActivityViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module{
    //retrofit Service
    single { retrofit().create(MoviesService::class.java) }
}

val databaseModule = module{
    single { MovieDatabase.getInstance(androidContext()).movieDao}
}

val repositoryModule = module {
    //repository
    single {Repository(get(), get())}
}

val appModule = module {
    //gallery ViewModel
    viewModel { GalleryViewModel(get()) }
    //activity View Model
    viewModel{ MainActivityViewModel(get())}
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