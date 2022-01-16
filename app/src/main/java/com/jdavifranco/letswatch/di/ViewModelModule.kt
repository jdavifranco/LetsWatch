package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.ui.main.MainActivityViewModel
import com.jdavifranco.letswatch.ui.moviedetails.DetailsViewModel
import com.jdavifranco.letswatch.ui.moviegallery.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GalleryViewModel(get()) }
    viewModel{ MainActivityViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}