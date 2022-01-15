package com.jdavifranco.letswatch.network.model

import com.jdavifranco.letswatch.database.model.Details
import com.jdavifranco.letswatch.network.infokeys.API_IMAGE_BASE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsDTO(
    val id:Long,
    val title: String,
    @Json(name="poster_path")val posterUrl:String?,
    @Json(name = "release_date")val releaseDate:String?,
    @Json(name = "vote_average") val voteAverage:Double,
    val overview:String?,
    val genres: NetworkGenres,
    val runtime:String?,
)

fun DetailsDTO.asDomainDetails(): Details {
    return Details(
        id = this.id,
        title =  this.title,
        poster = API_IMAGE_BASE_URL +(this.posterUrl?:""),
        date = this.releaseDate?:"",
        voteAverage = this.voteAverage,
        overview = this.overview ?: "",
        genres = this.genres.asDomainGenre(),
        runtime = runtime?:"",

    )
}