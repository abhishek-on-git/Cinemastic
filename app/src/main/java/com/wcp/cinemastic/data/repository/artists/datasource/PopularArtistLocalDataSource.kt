package com.wcp.cinemastic.data.repository.artists.datasource

import com.wcp.cinemastic.data.model.artists.PopularArtist

interface PopularArtistLocalDataSource {
    suspend fun fetchPopularArtistsFromDB(): List<PopularArtist>
    suspend fun persistPopularArtistsToDB(popularArtists: List<PopularArtist>)
    suspend fun clearAll()
}