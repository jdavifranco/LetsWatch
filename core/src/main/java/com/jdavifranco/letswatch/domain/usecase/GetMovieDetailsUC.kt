package com.jdavifranco.letswatch.domain.usecase

import com.jdavifranco.letswatch.domain.data.MovieDataRepository

class GetMovieDetailsUC(val repository: MovieDataRepository) {
    suspend operator fun invoke(id:Long) = repository.getMovieDetails(id)
}