package com.jdavifranco.letswatch.network.model

import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.network.infokeys.API_IMAGE_BASE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDTO (
    val id:Long,
    val title: String,
    @Json(name="poster_path")val posterUrl:String?,
    @Json(name = "release_date")val releaseDate:String?,
    @Json(name = "vote_average") val vote:Double
)

fun MovieDTO.asDomainMovie(): Movie {
    return Movie(
        id = this.id,
        title =  this.title,
        poster = API_IMAGE_BASE_URL +(this.posterUrl?:""),
        date = this.releaseDate?:"?",
        voteAverage = this.vote
    )
}

@JsonClass(generateAdapter = true)
data class NetworkMovies(@Json(name = "results")val results:List<MovieDTO>)
fun NetworkMovies.asDomainMovies():List<Movie>{
    return results.map {
       it.asDomainMovie()
    }
}
