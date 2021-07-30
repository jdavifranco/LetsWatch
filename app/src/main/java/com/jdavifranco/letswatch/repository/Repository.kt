package com.jdavifranco.letswatch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdavifranco.letswatch.network.MoviesDTO
import com.jdavifranco.letswatch.network.MoviesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val moviesService: MoviesService) {

    private val _movies = MutableLiveData<List<MoviesDTO>>()
    val movies:LiveData<List<MoviesDTO>>
    get() = _movies
    //function that gets the popular movies from service
    suspend fun refreshPopularMovies(){
        withContext(Dispatchers.IO){
            _movies.postValue(moviesService.getPopularMovies().results)
        }
    }

    suspend fun refreshMovieImages(pos:Int){
        withContext(Dispatchers.IO){
            _movies.value?.get(pos)?.let {
                val images = moviesService.getMovieImages(it.id!!)
                it.imagens = images.posters
            }
        }
    }

}