package com.jdavifranco.letswatch.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.network.MoviesService
import com.jdavifranco.letswatch.network.infokeys.MOVIES_API_KEY
import com.jdavifranco.letswatch.network.infokeys.MOVIES_BASE_URL

//Page index for the first request
private const val MOVIES_STARTING_PAGE_INDEX = 1
class MoviesPagingSource (
    private val service: MoviesService,
    private val query: String) :PagingSource<Int, Movie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key?: MOVIES_STARTING_PAGE_INDEX

    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }


}