package com.application.notesapp.di

import android.content.Context
import com.application.notesapp.data.NoteRepository
import com.application.notesapp.data.NoteRepositoryImpl
import com.application.notesapp.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRepository(@ApplicationContext context: Context): NoteRepository {
        return NoteRepositoryImpl(NotesDatabase.getDatabase(context).noteDao())
    }
}