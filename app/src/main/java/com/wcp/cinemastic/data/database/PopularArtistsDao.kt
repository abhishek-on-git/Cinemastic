package com.wcp.cinemastic.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.data.model.shows.PopularShow

@Dao
interface PopularArtistsDao {

    @Query("SELECT * FROM popular_artist")
    suspend fun fetchPopularArtists() : List<PopularArtist>

    @Query("DELETE FROM popular_artist")
    suspend fun clearPopularArtists()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistPopularArtists(movies : List<PopularArtist>)

}