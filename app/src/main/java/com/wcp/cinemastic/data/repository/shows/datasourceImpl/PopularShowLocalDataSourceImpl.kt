package com.wcp.cinemastic.data.repository.shows.datasourceImpl

import com.wcp.cinemastic.data.database.PopularShowsDao
import com.wcp.cinemastic.data.model.shows.PopularShow
import com.wcp.cinemastic.data.repository.shows.datasource.PopularShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularShowLocalDataSourceImpl(
    private val popularShowsDao: PopularShowsDao
) : PopularShowLocalDataSource {

    override suspend fun fetchPopularShows(): List<PopularShow> = popularShowsDao.fetchPopularShows()

    override suspend fun persistPopularShowsToDB(popularShows: List<PopularShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            popularShowsDao.persistPopularShows(popularShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            popularShowsDao.clearPopularShows()
        }
    }

}