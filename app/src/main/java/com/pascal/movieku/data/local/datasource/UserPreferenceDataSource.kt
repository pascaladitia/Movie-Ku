package com.pascal.movieku.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pascal.movieku.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserPreferenceDataSource {
    suspend fun setUsername(username: String)
    suspend fun getUsername(): Flow<String>
    suspend fun setBoarding(statusBoard: Boolean)
    suspend fun getBoarding(): Flow<Boolean>
    suspend fun logoutUser()
}

class UserPreferenceDataSourceImpl @Inject constructor(
    private val context: Context
): UserPreferenceDataSource {
    override suspend fun setUsername(username: String) {
        context.dataStore.edit {
            it[usernamePref] = username
        }
    }

    override suspend fun getUsername(): Flow<String> {
        return context.dataStore.data.map {
            it[usernamePref] ?: Constant.USERNAME_PREF
        }
    }

    override suspend fun setBoarding(statusBoard: Boolean) {
        context.dataStore.edit {
            it[boardingPref] = statusBoard
        }
    }

    override suspend fun getBoarding(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[boardingPref] ?: false
        }
    }

    override suspend fun logoutUser() {
        context.dataStore.edit {
            it.remove(usernamePref)
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(Constant.DATASTORE_PREF)
        private val usernamePref = stringPreferencesKey(Constant.USERNAME_PREF)
        private val boardingPref = booleanPreferencesKey(Constant.BOARDING_PREF)
    }
}