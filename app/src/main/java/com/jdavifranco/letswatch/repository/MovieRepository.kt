package com.jdavifranco.letswatch.repository

import com.jdavifranco.letswatch.datasource.mappers.toDomain
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource
import com.jdavifranco.letswatch.domain.data.MovieDataRepository
import com.jdavifranco.letswatch.domain.model.Details
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.domain.model.Movie
import retrofit2.HttpException

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
    ): MovieDataRepository{

    override suspend fun getMovieListByGenre(query: String, pagePosition:Int): List<Movie> {
        try {
            return remoteDataSource.getMoviesByGenre(query, pagePosition).toDomain()
        }catch (e:HttpException){
            throw e
        }
    }


    override suspend fun getGenreList() :List<Genre> {
        try {
           return remoteDataSource.getGenreList().toDomain()
        }catch (e:HttpException){
            throw e
       }
    }



    override suspend fun getMovieDetails(id: Long): Details {
        val detailsRM = remoteDataSource.getMovieDetails(id)
        return detailsRM.toDomain()
    }

}
/*
return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PagingSource(remoteDataSource, query) }
        ).flow
* */