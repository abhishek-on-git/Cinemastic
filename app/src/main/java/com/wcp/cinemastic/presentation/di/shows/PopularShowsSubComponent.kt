package com.wcp.cinemastic.presentation.di.shows

import com.wcp.cinemastic.presentation.shows.PopularShowActivity
import dagger.Subcomponent

@PopularShowsScope
@Subcomponent(modules = [PopularShowsModule::class])
interface PopularShowsSubComponent {
    fun inject(showActivity: PopularShowActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): PopularShowsSubComponent
    }
}