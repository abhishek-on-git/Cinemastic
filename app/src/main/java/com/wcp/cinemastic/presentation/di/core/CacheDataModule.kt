package com.wcp.cinemastic.presentation.di.core

import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistCacheDataSource
import com.wcp.cinemastic.data.repository.artists.datasourceImpl.PopularArtistCacheDataSourceImpl
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieCacheDataSource
import com.wcp.cinemastic.data.repository.movies.datasourceImpl.PopularMovieCacheDataSourceImpl
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowCacheDataSource
import com.wcp.cinemastic.data.repository.shows.datasourceImpl.PopularShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun providePopularMoviesCacheDataSource(): PopularMovieCacheDataSource {
        return PopularMovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providePopularShowsCacheDataSource(): PopularShowCacheDataSource {
        return PopularShowCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providePopularArtistsCacheDataSource(): PopularArtistCacheDataSource {
        return PopularArtistCacheDataSourceImpl()
    }
}