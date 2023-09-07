package com.pascal.movieku.presentation.ui.auth.register

import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.RegisterRequest
import kotlinx.coroutines.flow.StateFlow

interface RegisterContract {
    val registerUserResult: StateFlow<Resource<Unit>>
    fun registerUser(registerRequest: RegisterRequest)
}