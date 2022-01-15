package com.jdavifranco.letswatch.network.model

import com.jdavifranco.letswatch.database.model.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDTO(
    val id: Long,
    val name:String
)

@JsonClass(generateAdapter = true)
data class NetworkGenres(@Json(name= "genres")val genres: List<GenreDTO>)

fun NetworkGenres.asDomainGenre():List<Genre>{
    return genres.map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
}