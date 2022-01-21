package com.wcp.cinemastic.data.repository.shows.datasource

import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.model.shows.PopularShow

interface PopularShowLocalDataSource {
    suspend fun fetchPopularShows() : List<PopularShow>
    suspend fun persistPopularShowsToDB(popularMovies: List<PopularShow>)
    suspend fun clearAll()
}