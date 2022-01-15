package com.jdavifranco.letswatch.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdavifranco.letswatch.datasource.local.Movie
import com.jdavifranco.letswatch.datasource.local.model.Details
import com.jdavifranco.letswatch.datasource.local.model.Genre
import com.jdavifranco.letswatch.datasource.mappers.toLocal
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(private val remoteDataSource: RemoteDataSource) {

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList:LiveData<List<Genre>>
    get() = _genreList

    fun getMoviesStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(remoteDataSource, query) }
        ).flow
    }

    suspend fun getMoviesGenres() {
        withContext(Dispatchers.IO) {
            val popular = Genre(-1, "POPULAR")
            val genreList = mutableListOf(popular)
            genreList.addAll(remoteDataSource.getMovieGenres().toLocal())
            _genreList.postValue(genreList)
            }

    }


    suspend fun getMovieDetails(id: Long): Details {
        val detailsRM = remoteDataSource.getMovieDetails(id)
        return detailsRM.toLocal()
    }
}