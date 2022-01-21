package com.wcp.cinemastic.presentation.di.core

import android.content.Context
import androidx.core.content.contentValuesOf
import com.wcp.cinemastic.presentation.di.artists.PopularArtistsSubComponent
import com.wcp.cinemastic.presentation.di.movies.PopularMoviesSubComponent
import com.wcp.cinemastic.presentation.di.shows.PopularShowsSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    PopularArtistsSubComponent::class,
    PopularMoviesSubComponent::class,
    PopularShowsSubComponent::class]
)
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}