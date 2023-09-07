package com.pascal.movieku.presentation.ui.homepage.profile.user

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.StateFlow

interface ProfileUserContract {
    val getUserResult: StateFlow<Resource<UserEntity>>
    val updateProfileImageResult: StateFlow<Resource<Unit>>
    val logoutUserResult: StateFlow<Resource<Unit>>
    fun getUser()
    fun updateProfileImage(imageProfile: String)
    fun logoutUser()
}