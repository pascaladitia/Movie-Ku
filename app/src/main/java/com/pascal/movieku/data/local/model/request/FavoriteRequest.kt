package com.pascal.movieku.data.local.model.request

data class FavoriteRequest(
    val idMovie: Int,
    val username: String,
    val poster: String,
    val title: String,
    val overview: String,
    val rating: Double,
    val isFavorite: Boolean
)