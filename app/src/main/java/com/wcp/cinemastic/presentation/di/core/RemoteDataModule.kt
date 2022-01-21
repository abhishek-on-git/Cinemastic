package com.wcp.cinemastic.presentation.di.core

import com.wcp.cinemastic.data.api.TMDBService
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistRemoteDataSource
import com.wcp.cinemastic.data.repository.artists.datasourceImpl.PopularArtistRemoteDataSourceImpl
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieRemoteDataSource
import com.wcp.cinemastic.data.repository.movies.datasourceImpl.PopularMovieRemoteDataSourceImpl
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowRemoteDataSource
import com.wcp.cinemastic.data.repository.shows.datasourceImpl.PopularShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(val apiKey: String) {

    @Singleton
    @Provides
    fun providePopularMoviesRemoteDataSource(tmdbServie: TMDBService): PopularMovieRemoteDataSource{
        return PopularMovieRemoteDataSourceImpl(tmdbServie, apiKey)
    }

    @Singleton
    @Provides
    fun providePopularShowsRemoteDataSource(tmdbServie: TMDBService): PopularShowRemoteDataSource {
        return PopularShowRemoteDataSourceImpl(tmdbServie, apiKey)
    }

    @Singleton
    @Provides
    fun providePopularArtistsRemoteDataSource(tmdbServie: TMDBService): PopularArtistRemoteDataSource{
        return PopularArtistRemoteDataSourceImpl(tmdbServie, apiKey)
    }
}