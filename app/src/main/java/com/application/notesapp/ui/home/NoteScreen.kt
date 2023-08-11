package com.application.notesapp.ui.home

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.notesapp.data.localdb.Note
import com.application.notesapp.ui.addnotes.AddNotesActivity
import com.application.notesapp.ui.theme.NotesAppTheme
import com.application.notesapp.ui.viewmodel.NoteUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(modifier: Modifier = Modifier,
               noteUiState: NoteUiState = NoteUiState.Loading,
               isGridLayout: Boolean = true,
               searchNotesInputField: String = "",
               onDeleteNote: (Note) -> Unit = {}
               ) {

    val context = LocalContext.current

    when (noteUiState) {
        is NoteUiState.Loading -> {
            CircularProgressIndicator(modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center))
        }
        is NoteUiState.Failure -> {
            Icon(imageVector = Icons.Outlined.Error, contentDescription = null)
        }
        is NoteUiState.Success -> {
            Column(modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)) {

                NoteLazyColumn(notes = noteUiState.notes,
                    searchText = searchNotesInputField,
                    isGridLayout = isGridLayout,
                    onDeleteNote = onDeleteNote)
            }
        }
    }
}

@Composable
private fun NoteLazyColumn(modifier: Modifier = Modifier,
                           notes: List<Note> = emptyList(),
                           searchText: String = "",
                           isGridLayout: Boolean = true,
                           onDeleteNote: (Note) -> Unit = {}
                           ) {

    val context = LocalContext.current

    if (notes.isEmpty()) {
        Column(modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(imageVector = Icons.Outlined.Inbox,
                contentDescription = null,
                modifier = modifier.size(40.dp))

            Text(text = "Notes are empty")
        }

    } else {
        if (!isGridLayout) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(notes) {
                    if (it.text.startsWith(prefix = searchText, ignoreCase = true)) {
                        NoteCard(
                            note = it,
                            onNoteClick = {  selectedNote ->
                                AddNotesActivity.startActivity(activity = context as Activity, note = selectedNote)
                            },
                            onDeleteNote = onDeleteNote
                        )
                    }
                }
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(notes) {
                    if (it.text.startsWith(prefix = searchText, ignoreCase = true)) {
                        NoteCard(
                            note = it,
                            onNoteClick = {  selectedNote ->
                                AddNotesActivity.startActivity(activity = context as Activity, note = selectedNote)
                            },
                            onDeleteNote = onDeleteNote
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun NoteCard(modifier: Modifier = Modifier,
                     note: Note = Note(title = "", text = ""),
                     onNoteClick: (Note) -> Unit =  { _, -> },
                     onDeleteNote: (Note) -> Unit = {}) {

    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onLongClick = { onDeleteNote(note) },
                onClick = { onNoteClick(note) }
            ),
        border = BorderStroke(0.1.dp, color = Color.DarkGray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        ) {
        Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = note.title ?: "",
                style = MaterialTheme.typography.titleLarge)

            Text(text = note.text,
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NoteScreenLightThemePreview() {
    NotesAppTheme(darkTheme = false) {
        NoteScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NoteScreenDarkThemePreview() {
    NotesAppTheme(darkTheme = true) {
        NoteScreen()
    }
}