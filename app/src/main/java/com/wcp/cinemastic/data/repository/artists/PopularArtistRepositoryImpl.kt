package com.wcp.cinemastic.data.repository.artists

import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistCacheDataSource
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistLocalDataSource
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistRemoteDataSource
import com.wcp.cinemastic.domain.repository.PopularArtistRepository
import com.wcp.cinemastic.util.Logger
import java.lang.Exception

class PopularArtistRepositoryImpl(
    private val cacheDataSource: PopularArtistCacheDataSource,
    private val localDataSource: PopularArtistLocalDataSource,
    private val remoteDataSource: PopularArtistRemoteDataSource
): PopularArtistRepository {
    override suspend fun fetchPopularArtists(): List<PopularArtist>? = fetchPopularArtistsFromCache()

    override suspend fun updatePopularArtists(): List<PopularArtist>? {
        val updatedArtists = fetchPopularArtistsFromAPI()
        localDataSource.clearAll()
        localDataSource.persistPopularArtistsToDB(updatedArtists)
        cacheDataSource.cachePopularArtists(updatedArtists)
        return updatedArtists

    }

    suspend fun fetchPopularArtistsFromCache(): List<PopularArtist>{
        lateinit var popularArtists: List<PopularArtist>
        try {
            popularArtists = cacheDataSource.fetchPopularArtistsFromCache()
        } catch (e: Exception) {
            Logger.loge(
                "PopularArtistRepositoryImpl",
                "Error fetching popularArtists from cache source: ${e.message.toString()}"
            )
        }
        if(popularArtists.isNotEmpty()){
            return popularArtists
        } else {
            popularArtists = fetchPopularArtistsFromDB()
            cacheDataSource.cachePopularArtists(popularArtists)
            return popularArtists
        }
    }

    suspend fun fetchPopularArtistsFromDB(): List<PopularArtist>{
        lateinit var popularArtists: List<PopularArtist>
        try {
            popularArtists = localDataSource.fetchPopularArtistsFromDB()
        } catch (e: Exception) {
            Logger.loge(
                "PopularArtistRepositoryImpl",
                "Error fetching popularArtists from local source: ${e.message.toString()}"
            )
        }
        if(popularArtists.isNotEmpty()){
            return popularArtists
        } else {
            popularArtists = fetchPopularArtistsFromAPI()
            localDataSource.persistPopularArtistsToDB(popularArtists)
            return popularArtists
        }
    }

    suspend fun fetchPopularArtistsFromAPI(): List<PopularArtist>{
        lateinit var popularArtists: List<PopularArtist>
        try {
            val response = remoteDataSource.fetchPopularArtistsFromAPI()
            val responseBody = response.body()
            if(responseBody != null) {
                popularArtists = responseBody.popularArtists
            }
        } catch (e: Exception) {
            Logger.loge(
                "PopularArtistRepositoryImpl",
                "Error fetching popularArtists from remote source: ${e.message.toString()}"
            )
        }
        return popularArtists
    }
}