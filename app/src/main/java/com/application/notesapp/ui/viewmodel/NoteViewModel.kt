package com.application.notesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.notesapp.data.localdb.Note
import com.application.notesapp.data.localdb.NoteRepository
import com.application.notesapp.data.preference.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

sealed interface NoteUiState {
    object Loading : NoteUiState
    data class Success(val notes: List<Note> = emptyList()) : NoteUiState
    object Failure : NoteUiState
}
data class LayoutUiState(val isGridLayout: Boolean = true)
@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository, private val preferenceRepository: PreferenceRepository<Boolean>): ViewModel() {

    private companion object {
        const val TAG = "HomeViewModel"
        const val TIMEOUT_MILLIS = 5_000L
    }

    private var _selectedNoteUiState = MutableStateFlow(Note(title = "", text = ""))
    val selectedNoteUiState: StateFlow<Note> = _selectedNoteUiState

    val noteUiState: StateFlow<NoteUiState> = noteRepository.getAllNotes()
        .catch {
            NoteUiState.Loading
            if (it is IOException) {
                Timber.tag(TAG).e(message = "Error reading databases")
                NoteUiState.Failure
            } else {
                throw it
            }
        }
        .map { NoteUiState.Success(it) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = NoteUiState.Loading
            )

    val layoutUiState: StateFlow<LayoutUiState> =
        preferenceRepository.readPreference.map {
            LayoutUiState(it)
        }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = LayoutUiState()
            )

    fun updateLayoutPreference(value: Boolean) = viewModelScope.launch {
        preferenceRepository.updatePreference(value)
    }

    fun saveNotes(title: String = "", text: String = "") = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (text.isNotEmpty()) {
                noteRepository.insert(Note(title = title, text = text))
            }
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception.message.toString())
        }
    }

    fun updateNotes(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        try {
            noteRepository.update(note)
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception.message.toString())
        }
    }

    fun deleteNotes(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        try {
            noteRepository.delete(note)
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception.message.toString())
        }
    }

    fun updateSelectedNote(note: Note) {
        _selectedNoteUiState.value = note
    }
}