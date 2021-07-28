package com.jdavifranco.letswatch.network

import com.jdavifranco.letswatch.network.infokeys.MOVIES_API_KEY
import retrofit2.http.GET

//Interface that implements methods that create the url requests to the api
interface MoviesService {
    @GET("movie/popular?api_key=$MOVIES_API_KEY")
    fun getPopularMovies():NetworkMovies
}
