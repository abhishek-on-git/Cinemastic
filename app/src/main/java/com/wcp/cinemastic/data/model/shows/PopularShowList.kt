package com.wcp.cinemastic.data.model.shows


import com.google.gson.annotations.SerializedName
import com.wcp.cinemastic.data.model.shows.PopularShow

data class PopularShowList(
    @SerializedName("results")
    val popularShows: List<PopularShow>
)