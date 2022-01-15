package com.jdavifranco.letswatch.datasource.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListRM(@Json(name = "results")val movieList:List<MovieRM>)