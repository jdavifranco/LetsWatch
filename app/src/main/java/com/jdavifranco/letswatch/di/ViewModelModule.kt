package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.views.moviedetails.DetailsViewModel
import com.jdavifranco.letswatch.views.moviegallery.GalleryViewModel
import com.jdavifranco.letswatch.views.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GalleryViewModel(get()) }
    viewModel{ MainActivityViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}