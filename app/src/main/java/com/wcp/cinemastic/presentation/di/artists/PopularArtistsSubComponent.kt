package com.wcp.cinemastic.presentation.di.artists

import com.wcp.cinemastic.presentation.artists.PopularArtistsActivity
import dagger.Subcomponent

@PopularArtistsScope
@Subcomponent(modules = [PopularArtistsModule::class])
interface PopularArtistsSubComponent {
    fun inject(artistsActivity: PopularArtistsActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): PopularArtistsSubComponent
    }
}