package com.pascal.movieku.data.repository

import com.pascal.movieku.base.arch.BaseRepository
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.datasource.LocalDataSource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RegisterRepository {
    suspend fun registerUser(registerRequest: RegisterRequest): Flow<Resource<Unit>>
    suspend fun getUser(username: String): Flow<Resource<UserEntity>>
}

class RegisterRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): RegisterRepository, BaseRepository() {
    override suspend fun registerUser(registerRequest: RegisterRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.registerUser(registerRequest) })
    }

    override suspend fun getUser(username: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.getUser(username).first() })
    }
}