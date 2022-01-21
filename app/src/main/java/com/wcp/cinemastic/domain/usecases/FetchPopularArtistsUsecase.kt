package com.wcp.cinemastic.domain.usecases

import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.domain.repository.PopularArtistRepository

class FetchPopularArtistsUsecase(private val repository: PopularArtistRepository) {
    suspend fun execute() : List<PopularArtist>? = repository.fetchPopularArtists()
}