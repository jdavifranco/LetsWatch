package com.jdavifranco.letswatch.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jdavifranco.letswatch.network.NetworkMovies
import com.jdavifranco.letswatch.repository.Repository

class GalleryViewModel(private val repository: Repository) : ViewModel() {
    val networkMovies = MutableLiveData<NetworkMovies>()

    init {
        getMovies()
    }


    fun getMovies(){
        networkMovies.postValue(repository.getPopularMovies())
    }
}