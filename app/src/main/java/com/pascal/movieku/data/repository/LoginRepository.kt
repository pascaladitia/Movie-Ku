package com.pascal.movieku.data.repository

import com.pascal.movieku.base.arch.BaseRepository
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.datasource.LocalDataSource
import com.pascal.movieku.data.local.datasource.UserPreferenceDataSource
import com.pascal.movieku.data.local.model.request.LoginRequest
import com.pascal.movieku.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Flow<Resource<UserEntity>>
    suspend fun setUsername(username: String): Flow<Resource<Unit>>
}

class LoginRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource
): LoginRepository, BaseRepository() {
    override suspend fun loginUser(loginRequest: LoginRequest): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.loginUser(loginRequest).first() })
    }

    override suspend fun setUsername(username: String): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDataSource.setUsername(username) })
    }
}