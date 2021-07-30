package com.jdavifranco.letswatch.ui.gallery


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.repository.Repository
import kotlinx.coroutines.launch

class GalleryViewModel(private val repository: Repository) : ViewModel() {
    val movies = repository.movies

    init {
        refreshMovies()
    }

    private fun refreshMovies(){
        viewModelScope.launch {
            repository.getPopularMovies()
        }
    }
}