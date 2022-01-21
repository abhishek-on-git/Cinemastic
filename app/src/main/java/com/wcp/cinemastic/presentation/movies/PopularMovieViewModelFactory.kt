package com.wcp.cinemastic.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wcp.cinemastic.domain.usecases.FetchPopularMoviesUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularMovieUsecase

class PopularMovieViewModelFactory(
    private val fetchUsecase: FetchPopularMoviesUsecase,
    private val updateUsecase: UpdatePopularMovieUsecase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularMovieViewModel(fetchUsecase, updateUsecase) as T
    }
}