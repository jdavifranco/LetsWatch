package com.jdavifranco.letswatch.datasource.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieRM (
    val id:Long,
    val title: String,
    @Json(name="poster_path")val posterUrl:String?,
    @Json(name = "release_date")val releaseDate:String?,
    @Json(name = "vote_average") val vote:Double
)


