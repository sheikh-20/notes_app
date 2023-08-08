package com.application.notesapp.data.preference

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

interface PreferenceRepository<T> {

    val readPreference: Flow<T>
    suspend fun updatePreference(value: T)
}

class LayoutPreferenceRepositoryImpl constructor(private val dataStore: DataStore<Preferences>): PreferenceRepository<Boolean> {

    private companion object {
        val IS_GRID_LAYOUT = booleanPreferencesKey("is_grid_layout")
        const val TAG = "LayoutPreferenceRepositoryImpl"
    }

    override val readPreference: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException) {
                Timber.tag(TAG).e(message = "Error reading preferences.")
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[IS_GRID_LAYOUT] ?: true
        }

    override suspend fun updatePreference(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_GRID_LAYOUT] = value
        }
    }
}