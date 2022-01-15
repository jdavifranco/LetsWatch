package com.jdavifranco.letswatch.network

import com.jdavifranco.letswatch.network.infokeys.MOVIES_API_KEY
import com.jdavifranco.letswatch.network.model.DetailsDTO
import com.jdavifranco.letswatch.network.model.NetworkGenres
import com.jdavifranco.letswatch.network.model.NetworkMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("genre/movie/list?api_key=$MOVIES_API_KEY")
    suspend fun getGenresOfMovies(): NetworkGenres

    @GET("discover/movie?api_key=$MOVIES_API_KEY")
    suspend fun getMoviesOfGenre(
        @Query("with_genres") genreId:String,
        @Query("page") page:Int
    ):NetworkMovies

    @GET("movie/popular?api_key=$MOVIES_API_KEY")
    suspend fun getPopularMovies(@Query("page") page:Int): NetworkMovies

    @GET("movie/{id}?api_key=$MOVIES_API_KEY")
    suspend fun getMovieById(@Path("id")id:Long): DetailsDTO


}
