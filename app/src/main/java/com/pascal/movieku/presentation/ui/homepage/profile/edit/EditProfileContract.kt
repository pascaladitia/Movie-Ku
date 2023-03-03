package com.pascal.movieku.presentation.ui.homepage.profile.edit

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.EditProfileRequest
import kotlinx.coroutines.flow.StateFlow

interface EditProfileContract {
    val getUserResult: StateFlow<Resource<UserEntity>>
    val updateProfileUserResult: StateFlow<Resource<Unit>>
    fun getUser()
    fun updateProfileUser(editProfileRequest: EditProfileRequest)
}