package com.wcp.cinemastic.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.wcp.cinemastic.data.database.PopularArtistsDao
import com.wcp.cinemastic.data.database.PopularMoviesDao
import com.wcp.cinemastic.data.database.PopularShowsDao
import com.wcp.cinemastic.data.database.TMDBRoomDatabase
import com.wcp.cinemastic.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDBModule {

    @Singleton
    @Provides
    fun providePopularMovieDB(context: Context): TMDBRoomDatabase {
        return Room.databaseBuilder(context, TMDBRoomDatabase::class.java, Constants.LOCAL_DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun providePopularMovieDao(database: TMDBRoomDatabase): PopularMoviesDao {
        return database.getPopularMoviesDao()
    }

    @Singleton
    @Provides
    fun providePopularShowsDao(database: TMDBRoomDatabase): PopularShowsDao {
        return database.getPopularShowsDao()
    }

    @Singleton
    @Provides
    fun providePopularArtistsDao(database: TMDBRoomDatabase): PopularArtistsDao {
        return database.getPopularArtistsDao()
    }

}