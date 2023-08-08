package com.application.notesapp.data.localdb

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>

    fun getNote(id: Int): Flow<Note>

    suspend fun insert(note: Note)

    suspend fun update(note: Note)

    suspend fun delete(note: Note)
}

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()

    override fun getNote(id: Int): Flow<Note> = dao.getNote(id)

    override suspend fun insert(note: Note) = dao.insert(note)

    override suspend fun update(note: Note) = dao.update(note)

    override suspend fun delete(note: Note) = dao.delete(note)

}