package com.wcp.cinemastic.presentation.di.shows

import com.wcp.cinemastic.domain.usecases.FetchPopularShowUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularMovieUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularShowsUsecase
import com.wcp.cinemastic.presentation.movies.PopularMovieViewModelFactory
import com.wcp.cinemastic.presentation.shows.PopularShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PopularShowsModule {

    @PopularShowsScope
    @Provides
    fun provideMoviesViewModelFactory(
        fetchUsecase: FetchPopularShowUsecase,
        updateUsecase: UpdatePopularShowsUsecase
    ): PopularShowViewModelFactory {
        return PopularShowViewModelFactory(fetchUsecase, updateUsecase)
    }
}