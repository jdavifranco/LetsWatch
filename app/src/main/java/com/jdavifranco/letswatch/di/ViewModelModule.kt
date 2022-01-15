package com.jdavifranco.letswatch.di

import com.jdavifranco.letswatch.ui.details.DetailsViewModel
import com.jdavifranco.letswatch.ui.gallery.GalleryViewModel
import com.jdavifranco.letswatch.ui.mainactivity.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GalleryViewModel(get()) }
    viewModel{ MainActivityViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}