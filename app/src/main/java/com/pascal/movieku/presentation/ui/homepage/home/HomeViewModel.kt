package com.pascal.movieku.presentation.ui.homepage.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.network.model.response.movie.MovieResponse
import com.pascal.movieku.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): HomeContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    private val _getPopularResult = MutableStateFlow<Resource<MovieResponse>>(Resource.Empty())
    private val _getUpComingResult = MutableStateFlow<Resource<MovieResponse>>(Resource.Empty())
    private val _getTopRatedResult = MutableStateFlow<Resource<MovieResponse>>(Resource.Empty())
    override val getUserResult: StateFlow<Resource<UserEntity>> = _getUserResult
    override val getPopularResult: StateFlow<Resource<MovieResponse>> = _getPopularResult
    override val getUpComingResult: StateFlow<Resource<MovieResponse>> = _getUpComingResult
    override val getTopRatedResult: StateFlow<Resource<MovieResponse>> = _getTopRatedResult

    override fun getUser() {
        viewModelScope.launch {
            homeRepository.getUsername().collect {
                homeRepository.getUser(it.data.toString()).collect { source ->
                    _getUserResult.value = Resource.Success(source.data)
                }
            }
        }
    }

    override fun getMovieListPopular() {
        _getPopularResult.value = Resource.Loading()
        viewModelScope.launch {
            homeRepository.getMovieListPopular().collect {
                _getPopularResult.value = Resource.Success(it.data)
            }
        }
    }

    override fun getMovieListUpComing() {
        _getUpComingResult.value = Resource.Loading()
        viewModelScope.launch {
            homeRepository.getMovieListUpComing().collect {
                _getUpComingResult.value = Resource.Success(it.data)
            }
        }
    }

    override fun getMovieListTopRated() {
        _getTopRatedResult.value = Resource.Loading()
        viewModelScope.launch {
            homeRepository.getMovieListTopRated().collect {
                _getTopRatedResult.value = Resource.Success(it.data)
            }
        }
    }
}