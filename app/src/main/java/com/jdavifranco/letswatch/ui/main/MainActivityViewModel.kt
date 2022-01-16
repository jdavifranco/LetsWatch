package com.jdavifranco.letswatch.views.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.datasource.repository.Repository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    val genres = repository.genreList

    init {
        refreshGenres()
    }

    private fun refreshGenres(){
        viewModelScope.launch {
            repository.getMoviesGenres()
        }
    }
}