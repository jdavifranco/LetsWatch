package com.jdavifranco.letswatch.ui.gallery


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jdavifranco.letswatch.domain.model.Movie
import com.jdavifranco.letswatch.domain.usecase.GetMovieListByGenreUC
import com.jdavifranco.letswatch.ui.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class GalleryViewModel(private val getMovieListByGenreUC: GetMovieListByGenreUC) : ViewModel() {
    private var _responseState = MutableLiveData<ResponseState<Flow<PagingData<Movie>>>>()
    val responseState:LiveData<ResponseState<Flow<PagingData<Movie>>>> get() = _responseState

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun fetchMovies(queryString: String){
        _responseState.postValue(ResponseState.Loading)
        try {
            _responseState.postValue(
                ResponseState.Success(
                    result = searchMovies(queryString)
                ))
        }catch (e:Exception){
            _responseState.postValue(ResponseState.Error(e))
        }

    }

    private fun searchMovies(queryString: String):Flow<PagingData<Movie>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString

        val newResult: Flow<PagingData<Movie>> = Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PagingSource(getMovieListByGenreUC, queryString) }
        ).flow
            .cachedIn(viewModelScope)

        currentSearchResult = newResult

        return newResult
    }



}