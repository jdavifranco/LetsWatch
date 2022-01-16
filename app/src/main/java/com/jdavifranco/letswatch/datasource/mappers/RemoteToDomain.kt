package com.jdavifranco.letswatch.datasource.mappers

import com.jdavifranco.letswatch.datasource.remote.infokeys.API_IMAGE_BASE_URL
import com.jdavifranco.letswatch.datasource.remote.model.*
import com.jdavifranco.letswatch.domain.model.Details
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.domain.model.Movie


fun MovieListRM.toDomain():List<Movie>{
    return movieList.map {
        it.toDomain()
    }
}

fun MovieRM.toDomain(): Movie {
    return Movie(
        id = this.id,
        title =  this.title,
        poster = API_IMAGE_BASE_URL +(this.posterUrl?:""),
        date = this.releaseDate?:"?",
        voteAverage = this.vote
    )
}

fun DetailsRM.toDomain(): Details {
    return Details(
        id = id,
        title =  title,
        poster = API_IMAGE_BASE_URL +(posterUrl ?:""),
        date = releaseDate ?:"",
        voteAverage = voteAverage,
        overview = overview ?: "",
        genreLMS = genres.toDomain(),
        runtime = runtime?:"",

        )
}

fun GenreListRM.toDomain(): List<Genre>{
    return genres.toDomain()
}

fun List<GenreRM>.toDomain():List<Genre>{
    return map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
}
