package com.wcp.cinemastic.presentation.di

import com.wcp.cinemastic.presentation.di.artists.PopularArtistsSubComponent
import com.wcp.cinemastic.presentation.di.movies.PopularMoviesSubComponent
import com.wcp.cinemastic.presentation.di.shows.PopularShowsSubComponent

interface Injector {
    fun buildPopularMoviesSubComponent(): PopularMoviesSubComponent
    fun buildPopularShowsSubComponent(): PopularShowsSubComponent
    fun buildPopularArtistsSubComponent(): PopularArtistsSubComponent
}