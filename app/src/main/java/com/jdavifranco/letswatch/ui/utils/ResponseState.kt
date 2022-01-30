package com.jdavifranco.letswatch.ui.utils

sealed class ResponseState<out T: Any>{
    object Loading: ResponseState<Nothing>()
    data class Error(val error:Throwable): ResponseState<Nothing>()
    data class Success<out T:Any>(val result: T): ResponseState<T>()
}