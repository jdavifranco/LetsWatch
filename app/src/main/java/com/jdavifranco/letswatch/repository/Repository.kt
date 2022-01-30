package com.jdavifranco.letswatch.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdavifranco.letswatch.datasource.mappers.toDomain
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource
import com.jdavifranco.letswatch.domain.model.Details
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import kotlin.Exception

class Repository(
    private val remoteDataSource: RemoteDataSource) {

    fun getMoviesStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PagingSource(remoteDataSource, query) }
        ).flow
    }


    suspend fun getGenreList() :List<Genre> {
        try {
           return remoteDataSource.getGenreList().toDomain()
        }catch (e:HttpException){
            throw e
       }
    }


    suspend fun getMovieDetails(id: Long): Details {
        val detailsRM = remoteDataSource.getMovieDetails(id)
        return detailsRM.toDomain()
    }
}