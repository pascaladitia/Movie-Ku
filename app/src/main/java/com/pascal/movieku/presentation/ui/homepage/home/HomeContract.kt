package com.pascal.movieku.presentation.ui.homepage.home

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.network.model.response.movie.MovieResponse
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    val getUserResult: StateFlow<Resource<UserEntity>>
    val getPopularResult: StateFlow<Resource<MovieResponse>>
    val getUpComingResult: StateFlow<Resource<MovieResponse>>
    val getTopRatedResult: StateFlow<Resource<MovieResponse>>
    fun getUser()
    fun getMovieListPopular()
    fun getMovieListUpComing()
    fun getMovieListTopRated()
}