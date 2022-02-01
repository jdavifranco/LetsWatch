package com.jdavifranco.letswatch.ui.gallery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jdavifranco.letswatch.domain.data.MovieDataRepository
import com.jdavifranco.letswatch.domain.model.Movie

import okio.IOException
import retrofit2.HttpException

private const val MOVIES_STARTING_PAGE_INDEX = 1
const val MOVIES_PAGE_SIZE = 5
class PagingSource (
    private val repository: MovieDataRepository,
    private val query: String
    ) :PagingSource<Int, Movie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key?: MOVIES_STARTING_PAGE_INDEX
        val apiQuery = query

        return try {
            val movieList = repository
                .getMovieListByGenre(apiQuery,position)

            val nextKey =
                if(movieList.isEmpty()) null
                else position + (params.loadSize / MOVIES_PAGE_SIZE)

            val prevKey =
                if (position == MOVIES_STARTING_PAGE_INDEX) null
                else position-1

            LoadResult.Page(
                data = movieList,
                prevKey = prevKey,
                nextKey = nextKey,
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