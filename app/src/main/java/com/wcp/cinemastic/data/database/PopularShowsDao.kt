package com.wcp.cinemastic.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wcp.cinemastic.data.model.shows.PopularShow

@Dao
interface PopularShowsDao {

    @Query("SELECT * FROM popular_shows")
    suspend fun fetchPopularShows() : List<PopularShow>

    @Query("DELETE FROM popular_shows")
    suspend fun clearPopularShows()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistPopularShows(movies : List<PopularShow>)

}