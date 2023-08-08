package com.application.notesapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.application.notesapp.data.localdb.NoteRepository
import com.application.notesapp.data.localdb.NoteRepositoryImpl
import com.application.notesapp.data.localdb.NotesDatabase
import com.application.notesapp.data.preference.LayoutPreferenceRepositoryImpl
import com.application.notesapp.data.preference.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val PREFERENCE_NAME = "preferences"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCE_NAME
    )

    @Provides
    @Singleton
    fun providesRepository(@ApplicationContext context: Context): NoteRepository {
        return NoteRepositoryImpl(NotesDatabase.getDatabase(context).noteDao())
    }

    @Provides
    @Singleton
    fun providesPreferenceRepository(@ApplicationContext context: Context): PreferenceRepository<Boolean> {
        return LayoutPreferenceRepositoryImpl(context.dataStore)
    }
}