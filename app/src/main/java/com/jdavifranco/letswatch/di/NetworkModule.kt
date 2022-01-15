package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.network.MoviesService
import com.jdavifranco.letswatch.network.infokeys.MOVIES_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module{
    single { retrofit().create(MoviesService::class.java) }
}

private fun moshiConverter() = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun retrofit() = Retrofit.Builder()
    .baseUrl(MOVIES_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshiConverter()))
    .build()