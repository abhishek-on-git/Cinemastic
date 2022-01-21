package com.wcp.cinemastic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.model.shows.PopularShow

@Database(
    entities = [PopularMovie::class, PopularShow::class, PopularArtist::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBRoomDatabase : RoomDatabase(){
    abstract fun getPopularMoviesDao() : PopularMoviesDao
    abstract fun getPopularShowsDao() : PopularShowsDao
    abstract fun getPopularArtistsDao() : PopularArtistsDao
}