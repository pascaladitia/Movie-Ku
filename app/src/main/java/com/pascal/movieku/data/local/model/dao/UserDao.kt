package com.pascal.movieku.data.local.model.dao

import androidx.room.*
import com.pascal.movieku.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE username_user = :username AND password_user = :password")
    fun loginUser(username: String?, password: String?): Flow<UserEntity>

    @Query("SELECT * FROM user_table WHERE username_user = :username")
    fun getUser(username: String?): Flow<UserEntity>

    @Query("SELECT * FROM user_table WHERE username_user = :username AND email_user = :email")
    fun findUser(username: String?, email: String?): Flow<UserEntity>

    @Query("UPDATE user_table SET password_user = :password WHERE username_user = :username")
    suspend fun updatePassword(username: String?, password: String?)

    @Query("UPDATE user_table SET image_profile = :imageProfile WHERE username_user = :username")
    suspend fun updateImageProfile(imageProfile: String?, username: String?)

    @Query("UPDATE user_table " +
            "SET name_user = :name, email_user = :email, age = :age, phone_number_user = :phoneNumber " +
            "WHERE username_user = :username")
    suspend fun updateProfileUser(
        username: String?,
        name: String?,
        email: String?,
        age: Int?,
        phoneNumber: String?
    )
}