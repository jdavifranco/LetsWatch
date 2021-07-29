package com.jdavifranco.letswatch.repository

import androidx.lifecycle.LiveData
import com.jdavifranco.letswatch.network.MoviesService
import com.jdavifranco.letswatch.network.NetworkMovies

class Repository(private val moviesService: MoviesService) {

    //function that gets the popular movies from service
    fun getPopularMovies():NetworkMovies{
         return moviesService.getPopularMovies()
    }

}