package com.application.notesapp.ui.addnotes

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.notesapp.ui.viewmodel.NoteViewModel
import java.util.Date

@Composable
fun AddNotesScreenApp(modifier: Modifier = Modifier,
                      viewModel: NoteViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val uiState by viewModel.selectedNoteUiState.collectAsState()

    AddNotesScreen(
        selectedTitle = uiState.title ?: "",
        selectedNote = uiState.text,
        onUpdateClick = { title, notes ->
            viewModel.updateNotes(uiState.copy(title = title, text = notes, updatedDate = Date().time))
            (context as Activity).finish()
        },
        onSaveClick = { title, notes ->
            viewModel.saveNotes(title = title, text = notes)
            (context as Activity).finish()
    })
}