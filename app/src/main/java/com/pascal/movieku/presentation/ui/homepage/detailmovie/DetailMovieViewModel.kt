package com.pascal.movieku.presentation.ui.homepage.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.FavoriteRequest
import com.pascal.movieku.data.network.model.response.movie.details.DetailMovieResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailReviewsResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailVideosResponse
import com.pascal.movieku.data.repository.DetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository
): DetailMovieContract, ViewModel() {
    private val _getMovieDetailResult = MutableStateFlow<Resource<DetailMovieResponse>>(Resource.Empty())
    private val _getVideosDetailResult = MutableStateFlow<Resource<DetailVideosResponse>>(Resource.Empty())
    private val _getReviewsDetailResult = MutableStateFlow<Resource<DetailReviewsResponse>>(Resource.Empty())
    private val _insertFavoriteResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    private val _getFavoriteByIdResult = MutableStateFlow<Resource<Boolean>>(Resource.Empty())
    private val _deleteFavoriteByIdResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())

    override val getMovieDetailResult: StateFlow<Resource<DetailMovieResponse>> = _getMovieDetailResult
    override val getVideosDetailResult: StateFlow<Resource<DetailVideosResponse>> = _getVideosDetailResult
    override val getReviewsDetailResult: StateFlow<Resource<DetailReviewsResponse>> = _getReviewsDetailResult
    override val insertFavoriteResult: StateFlow<Resource<Unit>> = _insertFavoriteResult
    override val getFavoriteByIdResult: StateFlow<Resource<Boolean>> = _getFavoriteByIdResult
    override val deleteFavoriteByIdResult: StateFlow<Resource<Unit>> = _deleteFavoriteByIdResult

    override fun getMovieDetail(idMovie: Int) {
        _getMovieDetailResult.value = Resource.Loading()
        viewModelScope.launch {
            detailMovieRepository.getMovieDetail(idMovie).collect {
                _getMovieDetailResult.value = Resource.Success(it.data)
            }
        }
    }

    override fun getVideosDetail(idMovie: Int) {
        _getVideosDetailResult.value = Resource.Loading()
        viewModelScope.launch {
            detailMovieRepository.getVideosDetail(idMovie).collect {
                _getVideosDetailResult.value = Resource.Success(it.data)
            }
        }
    }

    override fun getReviewsDetail(idMovie: Int) {
        _getReviewsDetailResult.value = Resource.Loading()
        viewModelScope.launch {
            detailMovieRepository.getReviewsDetail(idMovie).collect {
                _getReviewsDetailResult.value = Resource.Success(it.data)
            }
        }
    }

    override fun getFavoriteById(idMovie: Int) {
        viewModelScope.launch {
            detailMovieRepository.getFavoriteById(idMovie).collect {
                _getFavoriteByIdResult.value = Resource.Success(it.data?.isFavorite)
            }
        }
    }

    override fun stateFavorite(idMovie: Int) {
        viewModelScope.launch {
            detailMovieRepository.getFavoriteById(idMovie).collect {
                when (it.data?.isFavorite) {
                    null -> {
                        detailMovieRepository.getUsername().collect { source ->
                            detailMovieRepository.getMovieDetail(idMovie).collect { source1 ->
                                source1.data?.let { result ->
                                    detailMovieRepository.insertFavoriteMovie(
                                        FavoriteRequest(
                                            idMovie = idMovie,
                                            username = source.data.toString(),
                                            poster = result.posterPath,
                                            title = result.title.toString(),
                                            overview = result.overview.toString(),
                                            rating = result.voteAverage ?: 0.0,
                                            isFavorite = true
                                        )
                                    ).collect {
                                        _insertFavoriteResult.value = Resource.Success(message = "Movie saved to collection")
                                    }
                                }
                            }
                        }
                    }
                    else -> {
                        detailMovieRepository.deleteFavoriteById(idMovie).collect {
                            _deleteFavoriteByIdResult.value = Resource.Success(message = "Movie has deleted from collection")
                        }
                    }
                }
            }
        }
    }
}