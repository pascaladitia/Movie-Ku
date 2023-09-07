package com.pascal.movieku.presentation.ui.splashscreen

import com.pascal.movieku.base.model.Resource
import kotlinx.coroutines.flow.StateFlow

interface SplashScreenContract {
    val getBoardingResult: StateFlow<Resource<Boolean>>
    val getUsernameResult: StateFlow<Resource<String>>
    fun getBoarding()
    fun getUsername()
}