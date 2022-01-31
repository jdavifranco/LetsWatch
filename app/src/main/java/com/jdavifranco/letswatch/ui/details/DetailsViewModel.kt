package com.jdavifranco.letswatch.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.domain.datarepository.MovieDataRepository
import com.jdavifranco.letswatch.repository.MovieRepository
import com.jdavifranco.letswatch.domain.model.Details
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: MovieDataRepository) : ViewModel() {
    private var _details = MutableLiveData<Details>()
    val details: LiveData<Details>
        get() = _details

    fun refreshDetails(id:Long){
        viewModelScope.launch {
            _details.postValue(repository.getMovieDetails(id))
        }
    }
}