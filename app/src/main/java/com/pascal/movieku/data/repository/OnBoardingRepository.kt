package com.pascal.movieku.data.repository

import com.pascal.movieku.base.arch.BaseRepository
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.datasource.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface OnBoardingRepository {
    suspend fun setBoarding(boardStatus: Boolean): Flow<Resource<Unit>>
}

class OnBoardingRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource
): OnBoardingRepository, BaseRepository() {
    override suspend fun setBoarding(boardStatus: Boolean): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDataSource.setBoarding(boardStatus) })
    }
}