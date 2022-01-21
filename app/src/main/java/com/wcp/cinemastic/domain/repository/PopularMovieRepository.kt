package com.wcp.cinemastic.domain.repository

import com.wcp.cinemastic.data.model.movies.PopularMovie

interface PopularMovieRepository {
     suspend fun fetchPopularMovies(): List<PopularMovie>?
     suspend fun updatePopularMovies(): List<PopularMovie>?
}