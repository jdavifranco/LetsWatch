package com.jdavifranco.letswatch.repository

import com.jdavifranco.letswatch.datasource.mappers.toDomain
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource
import com.jdavifranco.letswatch.domain.data.MovieDataRepository
import com.jdavifranco.letswatch.domain.model.*
import kotlin.Exception

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
    ): MovieDataRepository{

    override suspend fun getMovieListByGenre(query: String, pagePosition:Int): List<Movie> {
        try {
            return remoteDataSource.getMoviesByGenre(query, pagePosition).toDomain()
        }catch (e:Exception) {
            throw RemoteConnectionException()
        }
    }


    override suspend fun getGenreList() :List<Genre> {
        try {
           return remoteDataSource.getGenreList().toDomain()
        }catch (e:Exception) {
            throw RemoteConnectionException()
        }
    }



    override suspend fun getMovieDetails(id: Long): Details {
        try {
            val detailsRM = remoteDataSource.getMovieDetails(id)
            return detailsRM.toDomain()
        }
        catch (e:Exception) {
            throw RemoteConnectionException()
        }
    }

}