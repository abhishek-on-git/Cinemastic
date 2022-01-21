package com.wcp.cinemastic.data.repository.shows.datasourceImpl

import com.wcp.cinemastic.data.model.shows.PopularShow
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowCacheDataSource

class PopularShowCacheDataSourceImpl : PopularShowCacheDataSource {

    private var cachedShows = ArrayList<PopularShow>()

    override suspend fun fetchPopularShowsFromCache(): List<PopularShow> {
        return cachedShows
    }

    override suspend fun cachePopularShows(popularShows: List<PopularShow>) {
        cachedShows.clear()
        cachedShows = ArrayList(popularShows)
    }
}