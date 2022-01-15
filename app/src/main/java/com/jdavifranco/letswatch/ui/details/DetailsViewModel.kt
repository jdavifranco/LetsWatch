package com.jdavifranco.letswatch.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.database.model.Details
import com.jdavifranco.letswatch.repository.Repository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var _details = MutableLiveData<com.jdavifranco.letswatch.database.model.Details>()
    val details: LiveData<Details>
        get() = _details

    fun refreshDetails(id:Long){
        viewModelScope.launch {
            _details.postValue(repository.getMovieAndDetailsById(id))
        }
    }
}