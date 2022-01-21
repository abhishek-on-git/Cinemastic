package com.wcp.cinemastic.domain.repository

import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.data.model.shows.PopularShow

interface PopularArtistRepository {
    suspend fun fetchPopularArtists(): List<PopularArtist>?
    suspend fun updatePopularArtists(): List<PopularArtist>?
}