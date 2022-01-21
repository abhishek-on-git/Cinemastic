package com.wcp.cinemastic.data.repository.movies.datasource

import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.model.movies.PopularMovieList
import retrofit2.Response

interface PopularMovieRemoteDataSource {
    suspend fun fetchPopularMovies() : Response<PopularMovieList>
}