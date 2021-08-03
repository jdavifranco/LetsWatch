package com.jdavifranco.letswatch.ui.gallery


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GalleryViewModel(private val repository: Repository) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    init{
    }


    fun searchMovie(queryString: String): Flow<PagingData<Movie>> {
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