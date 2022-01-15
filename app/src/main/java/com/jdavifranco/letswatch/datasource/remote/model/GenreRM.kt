package com.jdavifranco.letswatch.datasource.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreRM(
    val id: Long,
    val name:String
)



