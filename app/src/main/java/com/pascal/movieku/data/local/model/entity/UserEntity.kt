package com.pascal.movieku.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "name_user")
    var name: String?,
    @ColumnInfo(name = "image_profile")
    var imageProfile: String?,
    @ColumnInfo(name = "email_user")
    var email: String?,
    @ColumnInfo(name = "age")
    var age: Int?,
    @ColumnInfo(name = "phone_number_user")
    var phoneNumber: String?,
    @ColumnInfo(name = "username_user")
    var username: String?,
    @ColumnInfo(name = "password_user")
    var password: String?
)