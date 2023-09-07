package com.pascal.movieku.presentation.ui.auth.forget

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.ForgetPasswordRequest
import kotlinx.coroutines.flow.StateFlow

interface ForgetPasswordContract {
    val findUserResult: StateFlow<Resource<UserEntity>>
    fun findUser(forgetPasswordRequest: ForgetPasswordRequest)
}