package com.jdavifranco.letswatch.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.datasource.repository.Repository
import com.jdavifranco.letswatch.domain.model.Details
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var _details = MutableLiveData<Details>()
    val details: LiveData<Details>
        get() = _details

    fun refreshDetails(id:Long){
        viewModelScope.launch {
            _details.postValue(repository.getMovieDetails(id))
        }
    }
}