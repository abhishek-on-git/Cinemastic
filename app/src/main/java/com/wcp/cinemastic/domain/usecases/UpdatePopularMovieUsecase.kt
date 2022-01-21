package com.wcp.cinemastic.domain.usecases

import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.domain.repository.PopularMovieRepository

class UpdatePopularMovieUsecase(private val repository: PopularMovieRepository) {
    suspend fun execute() : List<PopularMovie>? = repository.updatePopularMovies()
}