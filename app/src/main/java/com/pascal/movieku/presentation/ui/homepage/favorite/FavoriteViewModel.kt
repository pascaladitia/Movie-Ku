package com.pascal.movieku.presentation.ui.homepage.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.FavoriteEntity
import com.pascal.movieku.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
): FavoriteContract, ViewModel() {
    private val _getFavoriteMovieResult = MutableStateFlow<Resource<List<FavoriteEntity>>>(Resource.Empty())
    override val getFavoriteMovieResult: StateFlow<Resource<List<FavoriteEntity>>> = _getFavoriteMovieResult

    override fun getFavoriteMovie() {
        viewModelScope.launch {
            favoriteRepository.getUsername().collect {
                favoriteRepository.getFavorite(it.data.toString()).collect { source ->
                    _getFavoriteMovieResult.value = Resource.Success(source.data)
                }
            }
        }
    }
}