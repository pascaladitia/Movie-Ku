package com.pascal.movieku.presentation.ui.homepage.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.EditProfileRequest
import com.pascal.movieku.data.repository.EditProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileRepository: EditProfileRepository
) : EditProfileContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    private val _updateProfileUserResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val getUserResult: StateFlow<Resource<UserEntity>> = _getUserResult
    override val updateProfileUserResult: StateFlow<Resource<Unit>> = _updateProfileUserResult

    override fun getUser() {
        viewModelScope.launch {
            editProfileRepository.getUsername().collect {
                editProfileRepository.getUser(it.data.toString()).collect { source ->
                    _getUserResult.value = Resource.Success(source.data)
                }
            }
        }
    }

    override fun updateProfileUser(editProfileRequest: EditProfileRequest) {
        viewModelScope.launch {
            editProfileRepository.getUsername().collect {
                editProfileRepository.updateProfileUser(
                    username = it.data.toString(),
                    editProfileRequest = editProfileRequest
                ).collect { source ->
                    _updateProfileUserResult.value = Resource.Success(source.data)
                }
            }
        }
    }
}