package com.wcp.cinemastic.data.repository.artists.datasourceImpl

import com.wcp.cinemastic.data.database.PopularArtistsDao
import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.data.repository.artists.datasource.PopularArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularArtistLocalDataSourceImpl(
    private val artistsDao: PopularArtistsDao
): PopularArtistLocalDataSource {
    override suspend fun fetchPopularArtistsFromDB(): List<PopularArtist> = artistsDao.fetchPopularArtists()

    override suspend fun persistPopularArtistsToDB(popularArtists: List<PopularArtist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistsDao.persistPopularArtists(popularArtists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistsDao.clearPopularArtists()
        }
    }
}