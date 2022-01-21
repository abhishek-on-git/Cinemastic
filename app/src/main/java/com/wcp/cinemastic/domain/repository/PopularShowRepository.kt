package com.wcp.cinemastic.domain.repository

import com.wcp.cinemastic.data.model.shows.PopularShow

interface PopularShowRepository {
    suspend fun fetchPopularShows(): List<PopularShow>?
    suspend fun updatePopularShows(): List<PopularShow>?
}