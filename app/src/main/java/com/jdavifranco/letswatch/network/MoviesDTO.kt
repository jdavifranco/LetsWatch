package com.jdavifranco.letswatch.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Classes that define data transfer objects to be received from the api
@JsonClass(generateAdapter = true)
data class NetworkMovies(val movies:List<MoviesDTO>)

@JsonClass(generateAdapter = true)
data class MoviesDTO (
        val id:Long?,
        val title: String?,
        @Json(name = "release_date")val releaseDate:String?,
        @Json(name = "vote_average") val vote:Double?,
        var images:List<String>?)

@JsonClass(generateAdapter = true)
data class ImagesDTO (val listImages:List<String>)

/*
@JsonClass(generateAdapter = true)
data class DetailsDTO(
        val genres:List<GenresDTO>,
        val overview:String?,
        val runtime:String
)
data class GenresDTO(val id:String, val name:String)
 */