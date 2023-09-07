package com.pascal.movieku.data.repository

import com.pascal.movieku.base.arch.BaseRepository
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.datasource.LocalDataSource
import com.pascal.movieku.data.local.datasource.UserPreferenceDataSource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.network.datasource.MovieDataSource
import com.pascal.movieku.data.network.model.response.movie.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HomeRepository {
    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun getUser(username: String): Flow<Resource<UserEntity>>
    suspend fun getMovieListPopular(): Flow<Resource<MovieResponse>>
    suspend fun getMovieListUpComing(): Flow<Resource<MovieResponse>>
    suspend fun getMovieListTopRated(): Flow<Resource<MovieResponse>>
}

class HomeRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource,
    private val movieDataSource: MovieDataSource
): HomeRepository, BaseRepository() {
    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }

    override suspend fun getUser(username: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.getUser(username).first() })
    }

    override suspend fun getMovieListPopular(): Flow<Resource<MovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesPopular() })
    }

    override suspend fun getMovieListUpComing(): Flow<Resource<MovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesUpComing() })
    }

    override suspend fun getMovieListTopRated(): Flow<Resource<MovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesTopRated() })
    }
}