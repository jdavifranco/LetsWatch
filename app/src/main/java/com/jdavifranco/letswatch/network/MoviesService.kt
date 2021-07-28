package com.jdavifranco.letswatch.network

import retrofit2.http.GET

//Interface that implements methods that create the url requests to the api
interface MoviesService {
    @GET()
    fun getPopularMovies()
    //Companion object that holds retrofit builder
    companion object{

    }
}
