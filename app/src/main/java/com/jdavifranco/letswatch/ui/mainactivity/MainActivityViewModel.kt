package com.jdavifranco.letswatch.ui.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.repository.Repository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    val genres = repository.genres

    init {
        refreshGenres()
    }

    fun refreshGenres(){
        viewModelScope.launch {
            repository.getMoviesGenres()
        }
    }
}