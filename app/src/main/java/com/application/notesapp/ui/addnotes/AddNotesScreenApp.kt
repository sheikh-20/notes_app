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
        selectedNote = uiState.text,
        onUpdateClick = {viewModel.updateNotes(uiState.copy(text = it, updatedDate = Date().time))
            (context as Activity).finish()
        },
        onSaveClick = { viewModel.saveNotes(it)
            (context as Activity).finish()
    })
}