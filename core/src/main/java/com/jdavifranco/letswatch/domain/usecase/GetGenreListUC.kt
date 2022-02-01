package com.jdavifranco.letswatch.domain.usecase

import com.jdavifranco.letswatch.domain.data.MovieDataRepository

class GetGenreListUC(private val repository: MovieDataRepository) {
    suspend operator fun invoke() = repository.getGenreList()
}