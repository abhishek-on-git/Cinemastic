package com.wcp.cinemastic.domain.usecases

import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.domain.repository.PopularMovieRepository

class FetchPopularMoviesUsecase(private val repository: PopularMovieRepository) {
    suspend fun execute(): List<PopularMovie>? = repository.fetchPopularMovies()
}