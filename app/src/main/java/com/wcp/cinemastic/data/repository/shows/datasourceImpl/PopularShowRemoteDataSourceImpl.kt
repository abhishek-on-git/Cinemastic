package com.wcp.cinemastic.data.repository.shows.datasourceImpl

import com.wcp.cinemastic.data.api.TMDBService
import com.wcp.cinemastic.data.model.shows.PopularShowList
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowRemoteDataSource
import retrofit2.Response

class PopularShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : PopularShowRemoteDataSource {

    override suspend fun fetchPopularShows(): Response<PopularShowList> = tmdbService.fetchPopularShows(apiKey)

}