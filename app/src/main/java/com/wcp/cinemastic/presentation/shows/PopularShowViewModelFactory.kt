package com.wcp.cinemastic.presentation.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wcp.cinemastic.domain.usecases.FetchPopularShowUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularShowsUsecase

class PopularShowViewModelFactory(
    private val fetchUsecase: FetchPopularShowUsecase,
    private val updateUsecase: UpdatePopularShowsUsecase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularShowViewModel(fetchUsecase, updateUsecase) as T
    }
}