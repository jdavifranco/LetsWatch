package com.jdavifranco.letswatch.domain.data

import com.jdavifranco.letswatch.domain.model.Details
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.domain.model.Movie

interface MovieDataRepository {
    suspend fun getMovieListByGenre(query:String, pagePosition:Int): List<Movie>
    suspend fun getMovieDetails(id:Long): Details
    suspend fun getMovieGenreList():List<Genre>
}