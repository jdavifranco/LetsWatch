package com.jdavifranco.letswatch.network

import com.jdavifranco.letswatch.database.Detalhes
import com.jdavifranco.letswatch.database.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Classes that define data transfer objects to be received from the api
@JsonClass(generateAdapter = true)
data class NetworkMovies(@Json(name = "results")val results:List<MoviesDTO>)
//Extension function to map network objects to domain objects
fun NetworkMovies.asDatabaseModel():List<Movie>{
        return results.map {
                Movie(
                        id = it.id,
                        title =  it.title,
                        poster = it.posterUrl,
                        date = it.releaseDate,
                        voteAverage = it.vote,
                        detalhes = null
                )
        }
}

@JsonClass(generateAdapter = true)
data class MoviesDTO (
        val id:Long,
        val title: String,
        @Json(name="poster_path")val posterUrl:String,
        @Json(name = "release_date")val releaseDate:String,
        @Json(name = "vote_average") val vote:Double)

@JsonClass(generateAdapter = true)
data class DetalhesDTO(
        val overview:String,
        val genres:List<Int>,
        val runtime:String
)
fun DetalhesDTO.toDetalhesDomain() = Detalhes(
        overview = this.overview,
        genres = this.genres.toString(),
        runtime = this.runtime,
        images = null
)

@JsonClass(generateAdapter = true)
data class ImagesDTO (
        @Json(name  = "backdrops")var backdrops:List<String>,
        @Json(name = "posters")var posters: List<String>
        )

fun ImagesDTO.toListOfUrlString():List<String>{
        var imagesUrl:MutableList<String> = mutableListOf()
        imagesUrl.addAll(posters)
        imagesUrl.addAll(backdrops)
        return imagesUrl
}