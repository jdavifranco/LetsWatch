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

fun List<GenreDTO>.asDomainList():List<Genre>{
    return map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
}
