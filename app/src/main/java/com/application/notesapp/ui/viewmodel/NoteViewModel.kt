package com.application.notesapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.notesapp.data.Note
import com.application.notesapp.data.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    private companion object {
        const val TAG = "HomeViewModel"
        const val TIMEOUT_MILLIS = 5_000L
    }

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

    fun saveNotes(text: String = "") = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (text.isNotEmpty()) {
                noteRepository.insert(Note(text = text))
            }
        } catch (exception: Exception) {
            Timber.tag(TAG).d(exception.message.toString())
        }
    }
}