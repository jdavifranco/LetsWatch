package com.jdavifranco.letswatch.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jdavifranco.letswatch.datasource.local.model.GenreLM
import com.jdavifranco.letswatch.datasource.repository.Repository
import com.jdavifranco.letswatch.domain.model.Genre

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    val genreList = liveData {
        emit(repository.getMoviesGenres())
    }

}