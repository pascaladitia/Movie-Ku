package com.pascal.movieku.presentation.ui.auth.forget.newpassword

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.NewPasswordRequest
import kotlinx.coroutines.flow.StateFlow

interface NewPasswordContract {
    val updateUserResult: StateFlow<Resource<Unit>>
    fun updateUser(newPasswordRequest: NewPasswordRequest)
}