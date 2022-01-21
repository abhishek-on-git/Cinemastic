package com.wcp.cinemastic.data.repository.shows.datasource

import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.model.shows.PopularShow

interface PopularShowCacheDataSource {
    suspend fun fetchPopularShowsFromCache() : List<PopularShow>
    suspend fun cachePopularShows(popularMovies: List<PopularShow>)
}