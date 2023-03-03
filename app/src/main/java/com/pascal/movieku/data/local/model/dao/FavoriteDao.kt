package com.pascal.movieku.data.local.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.movieku.data.local.model.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_table WHERE username = :username")
    fun getFavorite(username: String): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite_table WHERE idMovie = :idMovie")
    fun getFavoriteById(idMovie: Int): Flow<FavoriteEntity>

    @Query("DELETE FROM favorite_table WHERE idMovie = :idMovie")
    suspend fun deleteFavoriteById(idMovie: Int)
}