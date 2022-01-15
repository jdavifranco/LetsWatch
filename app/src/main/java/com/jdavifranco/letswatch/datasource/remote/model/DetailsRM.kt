package com.jdavifranco.letswatch.datasource.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsRM(
    val id:Long,
    val title: String,
    @Json(name="poster_path")val posterUrl:String?,
    @Json(name = "release_date")val releaseDate:String?,
    @Json(name = "vote_average") val voteAverage:Double,
    val overview:String?,
    val genres: List<GenreRM>,
    val runtime:String?,
)
