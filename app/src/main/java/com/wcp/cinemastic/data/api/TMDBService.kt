package com.wcp.cinemastic.data.api

import com.wcp.cinemastic.data.model.artists.PopularArtistList
import com.wcp.cinemastic.data.model.movies.PopularMovieList
import com.wcp.cinemastic.data.model.shows.PopularShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("api_key") apiKey : String): Response<PopularMovieList>

    @GET("tv/popular")
    suspend fun fetchPopularShows(@Query("api_key") apiKey : String) : Response<PopularShowList>

    @GET("person/popular")
    suspend fun fetchPopularArtists(@Query("api_key") apiKey: String) : Response<PopularArtistList>
}