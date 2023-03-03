package com.pascal.movieku.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    var idMovie: Int,
    var username: String,
    var poster: String,
    var title: String,
    var overview: String,
    var rating: Double,
    var isFavorite: Boolean
)
