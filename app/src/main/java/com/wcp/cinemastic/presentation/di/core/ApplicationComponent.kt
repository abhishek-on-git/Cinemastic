package com.wcp.cinemastic.presentation.di.core

import com.wcp.cinemastic.presentation.di.artists.PopularArtistsSubComponent
import com.wcp.cinemastic.presentation.di.movies.PopularMoviesSubComponent
import com.wcp.cinemastic.presentation.di.shows.PopularShowsSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
ApplicationModule::class,
CacheDataModule::class,
LocalDataModule::class,
LocalDBModule::class,
RemoteAPIModule::class,
RemoteDataModule::class,
RepositoryModule::class,
UsecaseModule::class
])
interface ApplicationComponent {

    fun popularMoviesSubcomponent(): PopularMoviesSubComponent.Factory
    fun popularShowsSubcomponent(): PopularShowsSubComponent.Factory
    fun popularArtistsSubcomponent(): PopularArtistsSubComponent.Factory

}