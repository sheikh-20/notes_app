package com.application.notesapp.ui.addnotes

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.notesapp.ui.viewmodel.NoteViewModel

@Composable
fun AddNotesScreenApp(modifier: Modifier = Modifier,
                      viewModel: NoteViewModel = hiltViewModel()) {

    val context = LocalContext.current

    AddNotesScreen(onSaveClick = {
        viewModel.saveNotes(it)
        (context as Activity).finish()
    })
}