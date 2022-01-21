package com.wcp.cinemastic.presentation.di.movies

import com.wcp.cinemastic.domain.usecases.FetchPopularMoviesUsecase
import com.wcp.cinemastic.domain.usecases.FetchPopularShowUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularMovieUsecase
import com.wcp.cinemastic.presentation.movies.PopularMovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PopularMoviesModule {

    @PopularMoviesScope
    @Provides
    fun provideMoviesViewModelFactory(
        fetchUsecase: FetchPopularMoviesUsecase,
        updateUsecase: UpdatePopularMovieUsecase
    ): PopularMovieViewModelFactory {
        return PopularMovieViewModelFactory(fetchUsecase, updateUsecase)
    }
}