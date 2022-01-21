package com.wcp.cinemastic.presentation

import android.app.Application
import com.wcp.cinemastic.BuildConfig
import com.wcp.cinemastic.presentation.di.Injector
import com.wcp.cinemastic.presentation.di.artists.PopularArtistsSubComponent
import com.wcp.cinemastic.presentation.di.core.*
import com.wcp.cinemastic.presentation.di.movies.PopularMoviesSubComponent
import com.wcp.cinemastic.presentation.di.shows.PopularShowsSubComponent

class CinemasticApplication: Application(), Injector {
    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(applicationContext))
            .remoteAPIModule(RemoteAPIModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }

    override fun buildPopularMoviesSubComponent(): PopularMoviesSubComponent {
        return appComponent.popularMoviesSubcomponent().create()
    }

    override fun buildPopularShowsSubComponent(): PopularShowsSubComponent {
        return appComponent.popularShowsSubcomponent().create()
    }

    override fun buildPopularArtistsSubComponent(): PopularArtistsSubComponent {
        return appComponent.popularArtistsSubcomponent().create()
    }
}