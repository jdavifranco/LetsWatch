package com.jdavifranco.letswatch.ui.main

import androidx.lifecycle.*
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.domain.usecase.GetGenreListUC
import com.jdavifranco.letswatch.ui.utils.*
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(private val getGenreListUC: GetGenreListUC) : ViewModel() {
    private val _responseState = MutableLiveData<ResponseState<List<Genre>>>()
    val responseState:LiveData<ResponseState<List<Genre>>>
        get()  = _responseState

    init {
        fetchGenreList()
    }

    fun fetchGenreList(){
        _responseState.postValue(ResponseState.Loading)
        viewModelScope.launch {
            try {
                _responseState.postValue(
                    ResponseState.Success(
                        result = getGenreListUC.invoke()
                    )
                )
            }catch (e:Exception){
                _responseState.postValue(ResponseState.Error(error = e))
            }
        }
    }


}

