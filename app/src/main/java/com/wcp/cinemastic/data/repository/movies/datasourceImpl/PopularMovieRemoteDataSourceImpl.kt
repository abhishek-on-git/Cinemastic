package com.wcp.cinemastic.data.repository.movies.datasourceImpl

import android.util.Log
import com.wcp.cinemastic.data.api.TMDBService
import com.wcp.cinemastic.data.model.movies.PopularMovieList
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieRemoteDataSource
import retrofit2.Response

class PopularMovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : PopularMovieRemoteDataSource {

    override suspend fun fetchPopularMovies(): Response<PopularMovieList> {
        val service =  tmdbService.fetchPopularMovies(apiKey)
        Log.i("Abhishek","service is ${tmdbService}, movies: ${service}")
        return service
    }

}