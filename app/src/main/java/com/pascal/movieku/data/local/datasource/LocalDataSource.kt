package com.pascal.movieku.data.local.datasource

import com.pascal.movieku.data.local.model.dao.FavoriteDao
import com.pascal.movieku.data.local.model.dao.UserDao
import com.pascal.movieku.data.local.model.entity.FavoriteEntity
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {
    suspend fun registerUser(registerRequest: RegisterRequest)
    suspend fun loginUser(loginRequest: LoginRequest): Flow<UserEntity>
    suspend fun getUser(username: String): Flow<UserEntity>
    suspend fun findUser(forgetPasswordRequest: ForgetPasswordRequest): Flow<UserEntity>
    suspend fun updatePassword(newPasswordRequest: NewPasswordRequest)
    suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest)
    suspend fun updateProfileUser(username: String, editProfileRequest: EditProfileRequest)
    suspend fun insertMovie(favoriteRequest: FavoriteRequest)
    suspend fun getFavoriteMovie(username: String): Flow<List<FavoriteEntity>>
    suspend fun getFavoriteById(movieId: Int): Flow<FavoriteEntity>
    suspend fun deleteFavorite(movieId: Int)
}

class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val favoriteDao: FavoriteDao
): LocalDataSource {
    override suspend fun registerUser(registerRequest: RegisterRequest) {
        return userDao.insertUser(
            with(registerRequest) {
                UserEntity(
                    id = null,
                    name = name,
                    imageProfile = imageProfile,
                    email = email,
                    age = age,
                    phoneNumber = phoneNumber,
                    username = username,
                    password = password
                )
            }
        )
    }

    override suspend fun loginUser(loginRequest: LoginRequest): Flow<UserEntity> {
        return userDao.loginUser(
            username = loginRequest.username,
            password = loginRequest.password
        )
    }

    override suspend fun getUser(username: String): Flow<UserEntity> {
        return userDao.getUser(username)
    }

    override suspend fun findUser(forgetPasswordRequest: ForgetPasswordRequest): Flow<UserEntity> {
        return userDao.findUser(
            username = forgetPasswordRequest.username,
            email = forgetPasswordRequest.email
        )
    }

    override suspend fun updatePassword(newPasswordRequest: NewPasswordRequest) {
        return userDao.updatePassword(
            username = newPasswordRequest.username,
            password = newPasswordRequest.password
        )
    }

    override suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest) {
        return userDao.updateImageProfile(
            imageProfile = profileUserRequest.imageProfile,
            username = profileUserRequest.usernameUser
        )
    }

    override suspend fun updateProfileUser(username: String, editProfileRequest: EditProfileRequest) {
        return userDao.updateProfileUser(
            username = username,
            name = editProfileRequest.name,
            email = editProfileRequest.email,
            age = editProfileRequest.age,
            phoneNumber = editProfileRequest.phoneNumber
        )
    }

    override suspend fun insertMovie(favoriteRequest: FavoriteRequest) {
        return favoriteDao.insertMovie(
            FavoriteEntity(
                idMovie = favoriteRequest.idMovie,
                username = favoriteRequest.username,
                poster = favoriteRequest.poster,
                title = favoriteRequest.title,
                overview = favoriteRequest.overview,
                rating = favoriteRequest.rating,
                isFavorite = favoriteRequest.isFavorite
            )
        )
    }

    override suspend fun getFavoriteMovie(username: String): Flow<List<FavoriteEntity>> {
        return favoriteDao.getFavorite(username)
    }

    override suspend fun getFavoriteById(movieId: Int): Flow<FavoriteEntity> {
        return favoriteDao.getFavoriteById(movieId)
    }

    override suspend fun deleteFavorite(movieId: Int) {
        return favoriteDao.deleteFavoriteById(movieId)
    }
}