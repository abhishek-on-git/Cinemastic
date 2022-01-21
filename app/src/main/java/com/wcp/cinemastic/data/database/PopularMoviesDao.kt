package com.wcp.cinemastic.data.database

import androidx.room.*
import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.model.shows.PopularShow
import retrofit2.http.DELETE

@Dao
interface PopularMoviesDao {

    @Query("SELECT * FROM popular_movies")
    suspend fun fetchPopularMovies() : List<PopularMovie>

    @Query("DELETE FROM popular_movies")
    suspend fun clearPopularMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistPopularMovies(movies : List<PopularMovie>)

}