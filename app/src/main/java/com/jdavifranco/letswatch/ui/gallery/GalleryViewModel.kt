package com.jdavifranco.letswatch.ui.gallery


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jdavifranco.letswatch.repository.Repository
import com.jdavifranco.letswatch.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GalleryViewModel(private val repository: Repository) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun searchMovies(queryString: String): Flow<PagingData<Movie>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Movie>> = repository.getMoviesStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult

        return newResult
    }



}