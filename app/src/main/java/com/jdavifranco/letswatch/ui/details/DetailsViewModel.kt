package com.jdavifranco.letswatch.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.letswatch.domain.model.Details
import com.jdavifranco.letswatch.domain.usecase.GetMovieDetailsUC
import com.jdavifranco.letswatch.ui.utils.ResponseState
import kotlinx.coroutines.launch

class DetailsViewModel(private val getMovieDetailsUCUC: GetMovieDetailsUC) : ViewModel() {
    private var _responseState = MutableLiveData<ResponseState<Details>>()
    val responseState : LiveData<ResponseState<Details>>
        get() = _responseState


    fun fetchDetails(id:Long){
        viewModelScope.launch {
            _responseState.postValue(ResponseState.Loading)

            try {
                _responseState.postValue(
                    ResponseState.Success(
                        result = getMovieDetailsUCUC.invoke(id)
                    )
                )
            }catch (e:Exception){
                _responseState.postValue(ResponseState.Error(e))
            }
        }
    }
}