package com.wcp.cinemastic.data.repository.artists.datasourceImpl

import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistCacheDataSource

class PopularArtistCacheDataSourceImpl : PopularArtistCacheDataSource {

    private var cachedArtists = ArrayList<PopularArtist>()

    override suspend fun fetchPopularArtistsFromCache(): List<PopularArtist> {
        return cachedArtists
    }

    override suspend fun cachePopularArtists(popularArtists: List<PopularArtist>) {
        cachedArtists.clear()
        cachedArtists = ArrayList(popularArtists)
    }
}