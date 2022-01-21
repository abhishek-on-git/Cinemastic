package com.wcp.cinemastic.presentation.di.core

import com.wcp.cinemastic.data.database.PopularArtistsDao
import com.wcp.cinemastic.data.database.PopularMoviesDao
import com.wcp.cinemastic.data.database.PopularShowsDao
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistLocalDataSource
import com.wcp.cinemastic.data.repository.artists.datasourceImpl.PopularArtistLocalDataSourceImpl
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieLocalDataSource
import com.wcp.cinemastic.data.repository.movies.datasourceImpl.PopularMovieLocalDataSourceImpl
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowLocalDataSource
import com.wcp.cinemastic.data.repository.shows.datasourceImpl.PopularShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun providePopularMovieLocalDataSource(popularMovieDao: PopularMoviesDao): PopularMovieLocalDataSource{
        return PopularMovieLocalDataSourceImpl(popularMovieDao)
    }

    @Singleton
    @Provides
    fun providePopularShowsLocalDataSource(popularShowDao: PopularShowsDao): PopularShowLocalDataSource{
        return PopularShowLocalDataSourceImpl(popularShowDao)
    }

    @Singleton
    @Provides
    fun providePopularArtistsLocalDataSource(popularArtistDao: PopularArtistsDao): PopularArtistLocalDataSource{
        return PopularArtistLocalDataSourceImpl(popularArtistDao)
    }
}