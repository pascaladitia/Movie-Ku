package com.pascal.movieku.di

import android.content.Context
import com.pascal.movieku.data.local.datasource.LocalDataSource
import com.pascal.movieku.data.local.datasource.LocalDataSourceImpl
import com.pascal.movieku.data.local.datasource.UserPreferenceDataSource
import com.pascal.movieku.data.local.model.dao.UserDao
import com.pascal.movieku.data.local.datasource.UserPreferenceDataSourceImpl
import com.pascal.movieku.data.local.model.dao.FavoriteDao
import com.pascal.movieku.data.network.datasource.MovieDataSource
import com.pascal.movieku.data.network.datasource.MovieDataSourceImpl
import com.pascal.movieku.data.network.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(userDao: UserDao, favoriteDao: FavoriteDao): LocalDataSource {
        return LocalDataSourceImpl(userDao, favoriteDao)
    }

    @Provides
    @Singleton
    fun provideUserPreferenceDataSource(@ApplicationContext context: Context): UserPreferenceDataSource {
        return UserPreferenceDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideMoviesDataSource(movieService: MovieService): MovieDataSource {
        return MovieDataSourceImpl(movieService)
    }
}