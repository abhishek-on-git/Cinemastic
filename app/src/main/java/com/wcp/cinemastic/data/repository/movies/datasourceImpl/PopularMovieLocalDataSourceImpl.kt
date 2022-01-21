package com.wcp.cinemastic.data.repository.movies.datasourceImpl

import com.wcp.cinemastic.data.database.PopularMoviesDao
import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.repository.movies.datasource.PopularMovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularMovieLocalDataSourceImpl(
    private val popularMoviesDao: PopularMoviesDao
) : PopularMovieLocalDataSource {

    override suspend fun fetchPopularMovies(): List<PopularMovie> = popularMoviesDao.fetchPopularMovies()

    override suspend fun persistPopularMoviesToDB(popularMovies: List<PopularMovie>) {
        CoroutineScope(Dispatchers.IO).launch {
            popularMoviesDao.persistPopularMovies(popularMovies)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            popularMoviesDao.clearPopularMovies()
        }
    }

}