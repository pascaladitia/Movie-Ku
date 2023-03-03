package com.pascal.movieku.presentation.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.RegisterRequest
import com.pascal.movieku.data.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
): RegisterContract, ViewModel() {
    private val _registerUserResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val registerUserResult: StateFlow<Resource<Unit>> = _registerUserResult

    override fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            registerRepository.getUser(username = registerRequest.username.toString()).collect {
                val userResult = it.data?.username
                if (userResult != registerRequest.username) {
                    registerRepository.registerUser(registerRequest).collect { source ->
                        _registerUserResult.value = Resource.Success(source.data, "Account has been registered")
                    }
                } else {
                    _registerUserResult.value = Resource.Error(message = "Username has been taken")
                }
            }
        }
    }
}