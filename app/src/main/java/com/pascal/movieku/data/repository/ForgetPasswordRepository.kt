package com.pascal.movieku.data.repository

import com.pascal.movieku.base.arch.BaseRepository
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.datasource.LocalDataSource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.ForgetPasswordRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ForgetPasswordRepository {
    suspend fun findUser(forgetPasswordRequest: ForgetPasswordRequest): Flow<Resource<UserEntity>>
}

class ForgetPasswordRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): ForgetPasswordRepository, BaseRepository() {
    override suspend fun findUser(forgetPasswordRequest: ForgetPasswordRequest): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.findUser(forgetPasswordRequest).first() })
    }
}