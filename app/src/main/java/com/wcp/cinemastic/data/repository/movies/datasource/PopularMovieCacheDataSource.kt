package com.wcp.cinemastic.data.repository.movies.datasource

import com.wcp.cinemastic.data.model.movies.PopularMovie

interface PopularMovieCacheDataSource {
    suspend fun fetchPopularMoviesFromCache() : List<PopularMovie>
    suspend fun cachePopularMovies(popularMovies: List<PopularMovie>)
}