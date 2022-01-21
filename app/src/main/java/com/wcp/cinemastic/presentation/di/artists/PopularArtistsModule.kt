package com.wcp.cinemastic.presentation.di.artists

import com.wcp.cinemastic.domain.usecases.FetchPopularArtistsUsecase
import com.wcp.cinemastic.domain.usecases.UpdatePopularArtistsUsecase
import com.wcp.cinemastic.presentation.artists.PopularArtistsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PopularArtistsModule {

    @PopularArtistsScope
    @Provides
    fun provideArtistViewModelFactory(
        getUsecase: FetchPopularArtistsUsecase,
        updateUsecase: UpdatePopularArtistsUsecase
    ): PopularArtistsViewModelFactory {
        return PopularArtistsViewModelFactory(getUsecase, updateUsecase)
    }
}