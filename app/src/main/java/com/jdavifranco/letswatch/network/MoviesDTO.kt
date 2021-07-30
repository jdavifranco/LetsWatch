package com.jdavifranco.letswatch.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Classes that define data transfer objects to be received from the api
@JsonClass(generateAdapter = true)
data class NetworkMovies(@Json(name = "results")val results:List<MoviesDTO>)

@JsonClass(generateAdapter = true)
data class MoviesDTO (
        val id:Long?,
        val title: String?,
        @Json(name = "release_date")val releaseDate:String?,
        @Json(name = "vote_average") val vote:Double?,
        var imagens:List<String>?)

@JsonClass(generateAdapter = true)
data class DetalhesDTO(
        val overview:String?,
        val genres:List<Int>,
        val runtime:String
)

@JsonClass(generateAdapter = true)
data class ImagesDTO (
        @Json(name  = "backdrops")var backdrops:List<String>?,
        @Json(name = "posters")var posters: List<String>?
        )

/*
@JsonClass(generateAdapter = true)
data class DetailsDTO(
        val genres:List<GenresDTO>,
        val overview:String?,
        val runtime:String
)
data class GenresDTO(val id:String, val name:String)
 */