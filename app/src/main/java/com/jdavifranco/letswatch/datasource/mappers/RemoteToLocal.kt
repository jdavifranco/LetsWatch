package com.jdavifranco.letswatch.datasource.mappers

import com.jdavifranco.letswatch.datasource.local.Movie
import com.jdavifranco.letswatch.datasource.local.model.Details
import com.jdavifranco.letswatch.datasource.local.model.Genre
import com.jdavifranco.letswatch.datasource.remote.infokeys.API_IMAGE_BASE_URL
import com.jdavifranco.letswatch.datasource.remote.model.*



fun MovieListRM.toLocal():List<Movie>{
    return movieList.map {
        it.toLocal()
    }
}

fun MovieRM.toLocal(): Movie {
    return Movie(
        id = this.id,
        title =  this.title,
        poster = API_IMAGE_BASE_URL +(this.posterUrl?:""),
        date = this.releaseDate?:"?",
        voteAverage = this.vote
    )
}

fun DetailsRM.toLocal(): Details {
    return Details(
        id = this.id,
        title =  this.title,
        poster = API_IMAGE_BASE_URL +(this.posterUrl?:""),
        date = this.releaseDate?:"",
        voteAverage = this.voteAverage,
        overview = this.overview ?: "",
        genres = this.genres.toLocal(),
        runtime = runtime?:"",

        )
}

fun GenreListRM.toLocal(): List<Genre>{
    return genres.toLocal()
}

fun List<GenreRM>.toLocal():List<Genre>{
    return map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
}
