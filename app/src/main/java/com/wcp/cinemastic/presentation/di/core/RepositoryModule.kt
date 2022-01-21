package com.wcp.cinemastic.presentation.di.core

import com.wcp.cinemastic.data.repository.artists.PopularArtistRepositoryImpl
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistCacheDataSource
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistLocalDataSource
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistRemoteDataSource
import com.wcp.cinemastic.data.repository.movies.PopularMovieRepositoryImpl
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieCacheDataSource
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieLocalDataSource
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieRemoteDataSource
import com.wcp.cinemastic.data.repository.shows.PopularShowRepositoryImpl
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowCacheDataSource
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowLocalDataSource
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowRemoteDataSource
import com.wcp.cinemastic.domain.repository.PopularArtistRepository
import com.wcp.cinemastic.domain.repository.PopularMovieRepository
import com.wcp.cinemastic.domain.repository.PopularShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providePopularMoviesRepository(
        cacheDataSource: PopularMovieCacheDataSource,
        localDataSource: PopularMovieLocalDataSource,
        remoteDataSource: PopularMovieRemoteDataSource
    ): PopularMovieRepository {
        return PopularMovieRepositoryImpl(cacheDataSource, localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun providePopularShowsRepository(
        cacheDataSource: PopularShowCacheDataSource,
        localDataSource: PopularShowLocalDataSource,
        remoteDataSource: PopularShowRemoteDataSource
    ): PopularShowRepository {
        return PopularShowRepositoryImpl(cacheDataSource, localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun providePopularArtistsRepository(
        cacheDataSource: PopularArtistCacheDataSource,
        localDataSource: PopularArtistLocalDataSource,
        remoteDataSource: PopularArtistRemoteDataSource
    ): PopularArtistRepository {
        return PopularArtistRepositoryImpl(cacheDataSource, localDataSource, remoteDataSource)
    }



}