package com.wcp.cinemastic.presentation.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wcp.cinemastic.domain.usecases.FetchPopularArtistsUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularArtistsUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularShowsUsecase

class PopularArtistsViewModelFactory(
    private val fetchUsecase: FetchPopularArtistsUsecase,
    private val updateUsecase: UpdatePopularArtistsUsecase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularArtistsViewModel(fetchUsecase, updateUsecase) as T
    }
}