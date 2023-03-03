package com.pascal.movieku.data.repository

import com.pascal.movieku.base.arch.BaseRepository
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.datasource.LocalDataSource
import com.pascal.movieku.data.local.datasource.UserPreferenceDataSource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.ProfileUserRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ProfileUserRepository {
    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun getUser(username: String): Flow<Resource<UserEntity>>
    suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest): Flow<Resource<Unit>>
    suspend fun logoutUser(): Flow<Resource<Unit>>
}

class ProfileUserRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource
): ProfileUserRepository, BaseRepository() {
    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }

    override suspend fun getUser(username: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.getUser(username).first() })
    }

    override suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.updateImageProfile(profileUserRequest) })
    }

    override suspend fun logoutUser(): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDataSource.logoutUser() })
    }
}