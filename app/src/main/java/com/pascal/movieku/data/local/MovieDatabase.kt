package com.pascal.movieku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pascal.movieku.data.local.model.dao.FavoriteDao
import com.pascal.movieku.data.local.model.dao.UserDao
import com.pascal.movieku.data.local.model.entity.FavoriteEntity
import com.pascal.movieku.data.local.model.entity.UserEntity

@Database(
    entities = [UserEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao
}