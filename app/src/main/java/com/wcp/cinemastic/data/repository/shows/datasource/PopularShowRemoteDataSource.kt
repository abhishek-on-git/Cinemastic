package com.wcp.cinemastic.data.repository.shows.datasource

import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.model.movies.PopularMovieList
import com.wcp.cinemastic.data.model.shows.PopularShowList
import retrofit2.Response

interface PopularShowRemoteDataSource {
    suspend fun fetchPopularShows() : Response<PopularShowList>
}