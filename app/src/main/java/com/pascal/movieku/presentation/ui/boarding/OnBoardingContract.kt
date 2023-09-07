package com.pascal.movieku.presentation.ui.boarding

import com.pascal.movieku.base.model.Resource
import kotlinx.coroutines.flow.StateFlow

interface OnBoardingContract {
    val setBoardingResult: StateFlow<Resource<Unit>>
    fun setBoarding()
}