package com.pascal.movieku.presentation.ui.auth.forget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.ForgetPasswordRequest
import com.pascal.movieku.data.repository.ForgetPasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val forgetPasswordRepository: ForgetPasswordRepository
): ForgetPasswordContract, ViewModel() {
    private val _findUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    override val findUserResult: StateFlow<Resource<UserEntity>> = _findUserResult

    override fun findUser(forgetPasswordRequest: ForgetPasswordRequest) {
        viewModelScope.launch {
            forgetPasswordRepository.findUser(forgetPasswordRequest).collect {
                if (it.data?.username == forgetPasswordRequest.username && it.data.email == forgetPasswordRequest.email) {
                    _findUserResult.value = Resource.Success()
                } else if(it.data?.username == forgetPasswordRequest.username || it.data?.email == forgetPasswordRequest.email) {
                    _findUserResult.value = Resource.Error(message = "Username or email is wrong")
                } else {
                    _findUserResult.value = Resource.Error(message = "Account not found")
                }
            }
        }
    }
}