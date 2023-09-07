package com.pascal.movieku.presentation.ui.homepage.favorite

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.FavoriteEntity
import kotlinx.coroutines.flow.StateFlow

interface FavoriteContract {
    val getFavoriteMovieResult: StateFlow<Resource<List<FavoriteEntity>>>
    fun getFavoriteMovie()
}