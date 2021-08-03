package com.jdavifranco.letswatch.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.network.MoviesService
import com.jdavifranco.letswatch.network.asDatabaseModel
import com.jdavifranco.letswatch.network.infokeys.MOVIES_API_KEY
import com.jdavifranco.letswatch.network.infokeys.MOVIES_BASE_URL
import okio.IOException
import retrofit2.HttpException

//Page index for the first request
private const val MOVIES_STARTING_PAGE_INDEX = 1
const val MOVIES_PAGE_SIZE = 5
class MoviesPagingSource (
    private val moviesService: MoviesService,
    private val query: String) :PagingSource<Int, Movie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key?: MOVIES_STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            val response = if(apiQuery=="-1") moviesService.getPopularMovies(position)
            else moviesService.getMoviesOfGenre(apiQuery,position)

            val movies = response.asDatabaseModel()
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

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }


}