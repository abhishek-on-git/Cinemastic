package com.wcp.cinemastic.data.repository.movies.datasourceImpl

import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieCacheDataSource

class PopularMovieCacheDataSourceImpl : PopularMovieCacheDataSource {

    private var cachedMovies = ArrayList<PopularMovie>()

    override suspend fun fetchPopularMoviesFromCache(): List<PopularMovie> {
        return cachedMovies
    }

    override suspend fun cachePopularMovies(popularMovies: List<PopularMovie>) {
        cachedMovies.clear()
        cachedMovies = ArrayList(popularMovies)
    }
}