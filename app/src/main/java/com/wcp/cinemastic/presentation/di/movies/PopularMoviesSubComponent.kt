package com.wcp.cinemastic.presentation.di.movies

import com.wcp.cinemastic.presentation.movies.PopularMovieActivity
import dagger.Subcomponent

@PopularMoviesScope
@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularMoviesSubComponent {
    fun inject(popularMoviesActivity: PopularMovieActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): PopularMoviesSubComponent
    }
}