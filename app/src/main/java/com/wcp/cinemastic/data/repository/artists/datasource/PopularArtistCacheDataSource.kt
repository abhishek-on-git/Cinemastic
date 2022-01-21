package com.wcp.cinemastic.data.repository.artists.datasource

import com.wcp.cinemastic.data.model.artists.PopularArtist

interface PopularArtistCacheDataSource {
    suspend fun fetchPopularArtistsFromCache(): List<PopularArtist>
    suspend fun cachePopularArtists(popularArtists: List<PopularArtist>)
}