package com.pascal.movieku.di

import android.content.Context
import androidx.room.Room
import com.pascal.movieku.data.local.MovieDatabase
import com.pascal.movieku.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase = Room
        .databaseBuilder(
            context,
            MovieDatabase::class.java,
            Constant.MOVIE_DB
        )
        .build()

    @Provides
    @Singleton
    fun provideUserDao(movieDatabase: MovieDatabase) = movieDatabase.userDao()

    @Provides
    @Singleton
    fun provideFavoriteDao(movieDatabase: MovieDatabase) = movieDatabase.favoriteDao()
}