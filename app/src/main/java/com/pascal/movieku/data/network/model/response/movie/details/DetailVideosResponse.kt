package com.pascal.movieku.data.network.model.response.movie.details

import com.google.gson.annotations.SerializedName

data class DetailVideosResponse(
    @SerializedName("results")
    val result: List<Videos?>?
) {
    data class Videos(
        @SerializedName("id")
        var id: String,

        @SerializedName("key")
        var key: String,

        @SerializedName("name")
        var name: String,
    )
}