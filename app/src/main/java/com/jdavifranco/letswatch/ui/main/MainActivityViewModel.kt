package com.jdavifranco.letswatch.ui.main

import androidx.lifecycle.*
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.repository.Repository
import com.jdavifranco.letswatch.ui.utils.*
import com.jdavifranco.letswatch.ui.utils.Loading
import com.jdavifranco.letswatch.ui.utils.Success
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    private val _responseState = MutableLiveData<ResponseState>()
    val responseState:LiveData<ResponseState>
        get()  = _responseState

    init {
        fetchGenreList()
    }

    fun fetchGenreList(){
        _responseState.postValue(Loading())
        viewModelScope.launch {
            try {
                _responseState.postValue(
                    Success(
                        genreList = repository.getMoviesGenres()
                    )
                )
            }catch (e:Exception){
                _responseState.postValue(Error())
            }
        }
    }


}

data class Success(val genreList:List<Genre>):Success()
