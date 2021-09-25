package com.jdavifranco.letswatch.network

import com.jdavifranco.letswatch.database.Detalhes
import com.jdavifranco.letswatch.database.Genre
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
                        poster = API_IMAGE_BASE_URL+(it.posterUrl?:""),
                        date = it.releaseDate?:"?",
                        voteAverage = it.vote,
                        detalhes = null
                )
        }
}

@JsonClass(generateAdapter = true)
data class MoviesDTO (
        val id:Long,
        val title: String,
        @Json(name="poster_path")val posterUrl:String?,
        @Json(name = "release_date")val releaseDate:String?,
        @Json(name = "vote_average") val vote:Double,
        val overview:String?,
        val genres:List<GenreDTO>?,
        val runtime:String?
        )

fun MoviesDTO.asDatabaseMovieModel():Movie{
        return Movie(
                id = this.id,
                title =  this.title,
                poster = API_IMAGE_BASE_URL+(this.posterUrl?:""),
                date = this.releaseDate?:"?",
                voteAverage = this.vote,
                detalhes = null
        )
}
fun MoviesDTO.asDetalhesDomain() = Detalhes(
        overview = this.overview?:"?",
        genres = this.genres?.map { it -> it.name }.toString(),
        runtime = this.runtime?:"?",
        images = null
)
/*
@JsonClass(generateAdapter = true)
data class DetalhesDTO(
        val overview:String?,
        val genres:List<GenreDTO>,
        val runtime:String?
)
fun DetalhesDTO.toDetalhesDomain() = Detalhes(
        overview = this.overview?:"?",
        genres = this.genres.map { it -> it.name }.toString(),
        runtime = this.runtime?:"?",
        images = null
)


 */

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

@JsonClass(generateAdapter = true)
data class GenreDTO(
        val id: Long,
         val name:String
)