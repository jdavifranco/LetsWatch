package com.jdavifranco.letswatch.datasource.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jdavifranco.letswatch.datasource.local.Movie
import com.jdavifranco.letswatch.datasource.mappers.toLocal
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource

import okio.IOException
import retrofit2.HttpException

private const val MOVIES_STARTING_PAGE_INDEX = 1
const val MOVIES_PAGE_SIZE = 5
class PagingSource (
    private val remoteDataSource: RemoteDataSource,
    private val query: String
    ) :PagingSource<Int, Movie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key?: MOVIES_STARTING_PAGE_INDEX
        val apiQuery = query

        return try {
            val movieListRM = if(apiQuery=="-1") remoteDataSource.getPopularMovies(position)
            else remoteDataSource.getMoviesByGenre(apiQuery,position)

            val movies = movieListRM.toLocal()
            val nextKey = if(movies.isEmpty()){
                null
            } else{
                position + (params.loadSize / MOVIES_PAGE_SIZE)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == MOVIES_STARTING_PAGE_INDEX) null else position - 1,
                nextKey=nextKey,
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }


}