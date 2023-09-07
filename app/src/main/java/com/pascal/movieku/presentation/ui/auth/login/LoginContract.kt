package com.pascal.movieku.presentation.ui.auth.login

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.LoginRequest
import com.pascal.movieku.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    val loginUserResult: StateFlow<Resource<UserEntity>>
    fun loginUser(loginRequest: LoginRequest)
}