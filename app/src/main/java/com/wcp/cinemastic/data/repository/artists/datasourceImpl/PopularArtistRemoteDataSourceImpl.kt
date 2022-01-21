package com.wcp.cinemastic.data.repository.artists.datasourceImpl

import com.wcp.cinemastic.data.api.TMDBService
import com.wcp.cinemastic.data.model.artists.PopularArtistList
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistRemoteDataSource
import retrofit2.Response

class PopularArtistRemoteDataSourceImpl constructor(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : PopularArtistRemoteDataSource {

    override suspend fun fetchPopularArtistsFromAPI(): Response<PopularArtistList> = tmdbService.fetchPopularArtists(apiKey)

}