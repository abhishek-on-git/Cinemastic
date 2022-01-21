package com.wcp.cinemastic.presentation.di.core

import com.wcp.cinemastic.domain.repository.PopularArtistRepository
import com.wcp.cinemastic.domain.repository.PopularMovieRepository
import com.wcp.cinemastic.domain.repository.PopularShowRepository
import com.wcp.cinemastic.domain.usecases.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {

    @Provides
    fun provideFetchPoularMoviesUsecase(repository: PopularMovieRepository): FetchPopularMoviesUsecase {
        return FetchPopularMoviesUsecase(repository)
    }

    @Provides
    fun provideFetchPoularShowsUsecase(repository: PopularShowRepository): FetchPopularShowUsecase {
        return FetchPopularShowUsecase(repository)
    }

    @Provides
    fun provideGetPoularArtistsUsecase(repository: PopularArtistRepository): FetchPopularArtistsUsecase {
        return FetchPopularArtistsUsecase(repository)
    }

    @Provides
    fun provideUpdatePopularMovieUsecase(repository: PopularMovieRepository): UpdatePopularMovieUsecase {
        return UpdatePopularMovieUsecase(repository)
    }

    @Provides
    fun provideUpdatePopularShowsUsecase(repository: PopularShowRepository): UpdatePopularShowsUsecase {
        return UpdatePopularShowsUsecase(repository)
    }

    @Provides
    fun provideUpdatePopularArtistUsecase(repository: PopularArtistRepository): UpdatePopularArtistsUsecase {
        return UpdatePopularArtistsUsecase(repository)
    }

}