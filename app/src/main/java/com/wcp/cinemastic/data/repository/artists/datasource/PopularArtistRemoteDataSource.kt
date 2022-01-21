package com.wcp.cinemastic.data.repository.artists.datasource

import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.data.model.artists.PopularArtistList
import retrofit2.Response

interface PopularArtistRemoteDataSource {
    suspend fun fetchPopularArtistsFromAPI(): Response<PopularArtistList>
}