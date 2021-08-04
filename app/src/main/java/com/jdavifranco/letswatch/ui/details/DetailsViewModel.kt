package com.jdavifranco.letswatch.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.repository.Repository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie


    //função para atualizar os dados de um filme, chamada para api
    fun resfreshDetailsMovies(id:Long){
        viewModelScope.launch {
            _movie.postValue(repository.getMovieAndDetailsById(id))
        }
    }
}