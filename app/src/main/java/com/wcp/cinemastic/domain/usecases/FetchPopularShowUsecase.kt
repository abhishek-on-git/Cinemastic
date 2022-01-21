package com.wcp.cinemastic.domain.usecases

import com.wcp.cinemastic.data.model.shows.PopularShow
import com.wcp.cinemastic.domain.repository.PopularShowRepository

class FetchPopularShowUsecase(private val repository: PopularShowRepository) {
    suspend fun execute(): List<PopularShow>? = repository.fetchPopularShows()
}