package com.jdavifranco.letswatch.domain.datarepository

import androidx.paging.PagingData
import com.jdavifranco.letswatch.domain.model.Details
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDataRepository {
    fun getMovieStream(query:String): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(id:Long):Details
    suspend fun getMovieGenreList():List<Genre>
}