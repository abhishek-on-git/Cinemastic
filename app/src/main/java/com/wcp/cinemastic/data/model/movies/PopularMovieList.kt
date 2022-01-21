package com.wcp.cinemastic.data.model.movies


import com.google.gson.annotations.SerializedName
import com.wcp.cinemastic.data.model.movies.PopularMovie

data class PopularMovieList(
    @SerializedName("results")
    val popularMovies: List<PopularMovie>
)