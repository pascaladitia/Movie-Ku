package com.pascal.movieku.presentation.ui.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.repository.SplashScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val splashScreenRepository: SplashScreenRepository
): SplashScreenContract, ViewModel(){
    private val _getBoardingResult = MutableStateFlow<Resource<Boolean>>(Resource.Empty())
    private val _getUsernameResult = MutableStateFlow<Resource<String>>(Resource.Empty())
    override val getBoardingResult: StateFlow<Resource<Boolean>> = _getBoardingResult
    override val getUsernameResult: StateFlow<Resource<String>> = _getUsernameResult

    override fun getBoarding() {
        viewModelScope.launch {
            splashScreenRepository.getBoarding().collect {
                _getBoardingResult.value = Resource.Success(it.data)
            }
        }
    }

    override fun getUsername() {
        viewModelScope.launch {
            splashScreenRepository.getUsername().collect {
                _getUsernameResult.value = Resource.Success(it.data)
            }
        }
    }
}