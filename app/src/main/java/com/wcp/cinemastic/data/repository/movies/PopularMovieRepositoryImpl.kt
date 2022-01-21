package com.wcp.cinemastic.data.repository.movies

import android.util.Log
import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieCacheDataSource
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieLocalDataSource
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieRemoteDataSource
import com.wcp.cinemastic.domain.repository.PopularMovieRepository
import com.wcp.cinemastic.util.Logger
import java.lang.Exception

class PopularMovieRepositoryImpl(
    private val cacheDataSource: PopularMovieCacheDataSource,
    private val localDataSource: PopularMovieLocalDataSource,
    private val remoteDataSource: PopularMovieRemoteDataSource
) : PopularMovieRepository {

    override suspend fun fetchPopularMovies(): List<PopularMovie>? = fetchMoviesFromCache()

    override suspend fun updatePopularMovies(): List<PopularMovie>? {
        val updatedMovies = fetchPopularMoviesFromAPI()
        localDataSource.clearAll()
        localDataSource.persistPopularMoviesToDB(updatedMovies)
        cacheDataSource.cachePopularMovies(updatedMovies)
        return updatedMovies
    }

    suspend fun fetchMoviesFromCache(): List<PopularMovie> {
        lateinit var movies: List<PopularMovie>
        try {
            movies = cacheDataSource.fetchPopularMoviesFromCache()
        } catch (e: Exception) {
            Logger.loge(
                "PopularMovieRepositoryImpl",
                "Error fetching popularMovies from cache source: ${e.message.toString()}"
            )
        }
        if(movies.isNotEmpty()) {
            return movies
        } else {
            movies = fetchPopularMoviesFromDB()
            cacheDataSource.cachePopularMovies(movies)
            return movies
        }
    }

    suspend fun fetchPopularMoviesFromDB(): List<PopularMovie> {
        lateinit var movies: List<PopularMovie>
        try {
            movies = localDataSource.fetchPopularMovies()
        } catch (e: Exception) {
            Logger.loge(
                "PopularMovieRepositoryImpl",
                "Error fetching popularMovies from local source: ${e.message.toString()}"
            )
        }
        if(movies.isNotEmpty()) {
            return movies
        } else {
            movies = fetchPopularMoviesFromAPI()
            localDataSource.persistPopularMoviesToDB(movies)
            return movies
        }
    }

    suspend fun fetchPopularMoviesFromAPI(): List<PopularMovie> {
        lateinit var movies: List<PopularMovie>
        try {
            Log.i("Abhishek", "Trying to get movies")
            val response = remoteDataSource.fetchPopularMovies()
            Log.i("Abhishek", "response: ${response.code()}")
            val responseBody = response.body()
            Log.i("Abhishek", "${response.body()}1")
            if(responseBody != null) {
                movies = responseBody.popularMovies
            }
        } catch (e: Exception) {
            Logger.loge(
                "PopularMovieRepositoryImpl",
                "Error fetching popularMovies from remote source: ${e.message.toString()}"
            )
        }
        return movies
    }
}