package com.wcp.cinemastic.data.repository.shows

import com.wcp.cinemastic.data.model.shows.PopularShow
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowCacheDataSource
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowLocalDataSource
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowRemoteDataSource
import com.wcp.cinemastic.domain.repository.PopularShowRepository
import com.wcp.cinemastic.util.Logger
import java.lang.Exception

class PopularShowRepositoryImpl(
    private val cacheDataSource: PopularShowCacheDataSource,
    private val localDataSource: PopularShowLocalDataSource,
    private val remoteDataSource: PopularShowRemoteDataSource
) : PopularShowRepository {
    override suspend fun fetchPopularShows(): List<PopularShow>? = fetchShowsFromCache()

    override suspend fun updatePopularShows(): List<PopularShow>? {
        val updatedShows = fetchPopularShowsFromAPI()
        localDataSource.clearAll()
        localDataSource.persistPopularShowsToDB(updatedShows)
        cacheDataSource.cachePopularShows(updatedShows)
        return updatedShows
    }

    suspend fun fetchShowsFromCache(): List<PopularShow> {
        lateinit var shows: List<PopularShow>
        try {
            shows = cacheDataSource.fetchPopularShowsFromCache()
        } catch (e: Exception) {
            Logger.loge(
                "PopularShowRepositoryImpl",
                "Error fetching popularShows from cache source: ${e.message.toString()}"
            )
        }

        if(shows.isNotEmpty()) {
            return shows
        } else {
            shows = fetchPopularShowsFromDB()
            cacheDataSource.cachePopularShows(shows)
            return shows
        }
    }

    suspend fun fetchPopularShowsFromDB(): List<PopularShow>{
        lateinit var shows: List<PopularShow>
        try {
            shows = localDataSource.fetchPopularShows()
        } catch (e: Exception) {
            Logger.loge(
                "PopularShowRepositoryImpl",
                "Error fetching popularShows from local source: ${e.message.toString()}"
            )
        }

        if(shows.isNotEmpty()) {
            return shows
        } else {
            shows = fetchPopularShowsFromAPI()
            localDataSource.persistPopularShowsToDB(shows)
            return shows
        }
    }
    suspend fun fetchPopularShowsFromAPI(): List<PopularShow>{
        lateinit var shows: List<PopularShow>
        try {
            val response = remoteDataSource.fetchPopularShows()
            val responseBody = response.body()
            if(responseBody != null) {
                shows = responseBody.popularShows
            }
        } catch (e: Exception) {
            Logger.loge(
                "PopularShowRepositoryImpl",
                "Error fetching popularShows from remote source: ${e.message.toString()}"
            )
        }
        return shows
    }
}