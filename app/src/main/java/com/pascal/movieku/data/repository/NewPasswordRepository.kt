package com.pascal.movieku.data.repository

import com.pascal.movieku.base.arch.BaseRepository
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.datasource.LocalDataSource
import com.pascal.movieku.data.local.model.request.NewPasswordRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NewPasswordRepository {
    suspend fun updatePassword(newPasswordRequest: NewPasswordRequest): Flow<Resource<Unit>>
}

class NewPasswordRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): NewPasswordRepository, BaseRepository() {
    override suspend fun updatePassword(newPasswordRequest: NewPasswordRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.updatePassword(newPasswordRequest) })
    }
}