package com.wcp.cinemastic.data.model.artists


import com.google.gson.annotations.SerializedName

data class PopularArtistList(
    @SerializedName("results")
    val popularArtists: List<PopularArtist>
)