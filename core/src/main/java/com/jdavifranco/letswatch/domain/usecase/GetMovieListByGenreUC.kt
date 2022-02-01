package com.jdavifranco.letswatch.domain.usecase

import com.jdavifranco.letswatch.domain.data.MovieDataRepository

class GetMovieListByGenreUC(private val repository: MovieDataRepository) {
    suspend operator fun invoke(query:String, pagePosition: Int) =
        repository.getMovieListByGenre(query, pagePosition)
}