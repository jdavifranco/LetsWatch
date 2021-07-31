package com.jdavifranco.letswatch.ui.gallery


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.repository.Repository
import kotlinx.coroutines.launch

class GalleryViewModel(private val repository: Repository) : ViewModel() {
    val movies = repository.movies
    var page:Int = 1

    init {
        getMovies(page)
    }

    fun getMovies(page:Int){
        viewModelScope.launch {
            repository.getPopularMovies(page)
        }
    }

    fun getNextPage(){
        page++
        viewModelScope.launch {
            Log.e("page", "$page")
            repository.getPopularMovies(page)
        }
    }


}