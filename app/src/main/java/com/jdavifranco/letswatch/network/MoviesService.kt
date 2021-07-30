package com.jdavifranco.letswatch.network

import android.media.Image
import com.jdavifranco.letswatch.network.infokeys.MOVIES_API_KEY
import retrofit2.http.GET

//Interface that implements methods that create the url requests to the api
interface MoviesService {
    @GET("movie/popular?api_key=$MOVIES_API_KEY")
    suspend fun getPopularMovies():NetworkMovies

    @GET("movie/{id}/images?api_key=$MOVIES_API_KEY")
    suspend fun getMovieImages(id:Long):ImagesDTO
}
