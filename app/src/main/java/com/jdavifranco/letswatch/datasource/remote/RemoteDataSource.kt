package com.jdavifranco.letswatch.datasource.remote

import com.jdavifranco.letswatch.datasource.remote.infokeys.MOVIES_API_KEY
import com.jdavifranco.letswatch.datasource.remote.model.DetailsRM
import com.jdavifranco.letswatch.datasource.remote.model.GenreListRM
import com.jdavifranco.letswatch.datasource.remote.model.MovieListRM
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {
    @GET("genre/movie/list?api_key=$MOVIES_API_KEY")
    fun getGenreList(): GenreListRM

    @GET("discover/movie?api_key=$MOVIES_API_KEY")
    fun getMoviesByGenre(
        @Query("with_genres") genreId:String,
        @Query("page") page:Int
    ):MovieListRM

    @GET("movie/{id}?api_key=$MOVIES_API_KEY")
    fun getMovieDetails(@Path("id")id:Long): DetailsRM


}
