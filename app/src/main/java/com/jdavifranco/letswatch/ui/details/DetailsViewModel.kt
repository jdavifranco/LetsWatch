package com.jdavifranco.letswatch.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.domain.model.Details
import com.jdavifranco.letswatch.domain.usecase.GetMovieDetailsUC
import kotlinx.coroutines.launch

class DetailsViewModel(private val getMovieDetailsUCUC: GetMovieDetailsUC) : ViewModel() {
    private var _details = MutableLiveData<Details>()
    val details: LiveData<Details>
        get() = _details

    fun refreshDetails(id:Long){
        viewModelScope.launch {
            _details.postValue(getMovieDetailsUCUC.invoke(id))
        }
    }
}