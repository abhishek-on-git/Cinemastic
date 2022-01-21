package com.wcp.cinemastic.presentation.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wcp.cinemastic.domain.usecases.FetchPopularShowUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularShowsUsecase

class PopularShowViewModel(
    private val fetchUsecase: FetchPopularShowUsecase,
    private val updateUsecase: UpdatePopularShowsUsecase
): ViewModel() {

    fun fetchPopularShows() = liveData {
        val popularShows = fetchUsecase.execute()
        emit(popularShows)
    }

    fun updatePopularShows() = liveData {
        val popularShows = updateUsecase.execute()
        emit(popularShows)
    }
}