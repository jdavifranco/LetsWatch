package com.jdavifranco.letswatch.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jdavifranco.letswatch.datasource.repository.Repository

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    val genreList = liveData {
        emit(repository.getGenreList())
    }

}