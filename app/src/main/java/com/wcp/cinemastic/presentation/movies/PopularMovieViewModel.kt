package com.wcp.cinemastic.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wcp.cinemastic.domain.usecases.FetchPopularMoviesUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularArtistsUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularMovieUsecase

class PopularMovieViewModel(
    private val fetchUsecase: FetchPopularMoviesUsecase,
    private val updateUsecase: UpdatePopularMovieUsecase
): ViewModel() {

    fun fetchPopularMovies() = liveData {
        val popularMovies = fetchUsecase.execute()
        emit(popularMovies)
    }

    fun updatePopularMovies() = liveData {
        val movies = updateUsecase.execute()
        emit(movies)
    }
}