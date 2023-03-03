package com.pascal.movieku.presentation.ui.homepage.detailmovie

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.network.model.response.movie.details.DetailMovieResponse
import kotlinx.coroutines.flow.StateFlow

interface DetailMovieContract {
    val getMovieDetailResult: StateFlow<Resource<DetailMovieResponse>>
    val insertFavoriteResult: StateFlow<Resource<Unit>>
    val getFavoriteByIdResult: StateFlow<Resource<Boolean>>
    val deleteFavoriteByIdResult: StateFlow<Resource<Unit>>
    fun getMovieDetail(idMovie: Int)
    fun getFavoriteById(idMovie: Int)
    fun stateFavorite(idMovie: Int)
}