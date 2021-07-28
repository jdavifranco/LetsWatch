package com.jdavifranco.letswatch.network

import retrofit2.http.GET

//Interface that implements methods that create the url requests to the api
interface MoviesService {
    @GET()
    fun getPopularMovies()
    //Companion object that holds retrofit builder
    companion object{
        private const val MOVIES_BASE_URL="https://api.themoviedb.org/3/movie/popular?api_key=1dc1f4287000496529dc69938e21db46&page=1"
    }
}
