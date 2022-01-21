package com.wcp.cinemastic.data.repository.movies.datasource

import com.wcp.cinemastic.data.model.movies.PopularMovie

interface PopularMovieLocalDataSource {
    suspend fun fetchPopularMovies() : List<PopularMovie>
    suspend fun persistPopularMoviesToDB(popularMovies: List<PopularMovie>)
    suspend fun clearAll()
}