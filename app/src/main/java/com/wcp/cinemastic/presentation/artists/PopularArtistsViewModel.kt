package com.wcp.cinemastic.presentation.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wcp.cinemastic.domain.usecases.FetchPopularArtistsUsecase
import com.wcp.cinemastic.domain.usecases.FetchPopularShowUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularArtistsUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularShowsUsecase

class PopularArtistsViewModel(
    private val fetchUsecase: FetchPopularArtistsUsecase,
    private val updateUsecase: UpdatePopularArtistsUsecase
): ViewModel() {

    fun fetchPopularArtists() = liveData {
        val popularArtists = fetchUsecase.execute()
        emit(popularArtists)
    }

    fun updatePopularArtists() = liveData {
        val popularArtists = updateUsecase.execute()
        emit(popularArtists)
    }
}