package com.pascal.movieku.presentation.ui.homepage.profile.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.ProfileUserRequest
import com.pascal.movieku.data.repository.ProfileUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val profileUserRepository: ProfileUserRepository
): ProfileUserContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    private val _updateProfileImageResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    private val _logoutUserResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val getUserResult: StateFlow<Resource<UserEntity>> = _getUserResult
    override val updateProfileImageResult: StateFlow<Resource<Unit>> = _updateProfileImageResult
    override val logoutUserResult: StateFlow<Resource<Unit>> = _logoutUserResult

    override fun getUser() {
        viewModelScope.launch {
            profileUserRepository.getUsername().collect {
                profileUserRepository.getUser(it.data.toString()).collect { source ->
                    _getUserResult.value = Resource.Success(source.data)
                }
            }
        }
    }

    override fun updateProfileImage(imageProfile: String) {
        viewModelScope.launch {
            profileUserRepository.getUsername().collect {
                profileUserRepository.updateImageProfile(
                    ProfileUserRequest(
                        imageProfile = imageProfile,
                        usernameUser = it.data.toString()
                    )
                ).collect { source ->
                    _updateProfileImageResult.value = Resource.Success(source.data)
                }
            }
        }
    }

    override fun logoutUser() {
        viewModelScope.launch {
            profileUserRepository.logoutUser().collect {
                _logoutUserResult.value = Resource.Success()
            }
        }
    }
}