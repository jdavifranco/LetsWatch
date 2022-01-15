package com.jdavifranco.letswatch.datasource.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreListRM(@Json(name= "genres")val genres: List<GenreRM>)