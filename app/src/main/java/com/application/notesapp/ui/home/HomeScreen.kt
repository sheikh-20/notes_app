package com.application.notesapp.ui.home

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Splitscreen
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.notesapp.data.Note
import com.application.notesapp.ui.addnotes.AddNotesActivity
import com.application.notesapp.ui.editnotes.EditNotesActivity
import com.application.notesapp.ui.theme.NotesAppTheme
import com.application.notesapp.ui.viewmodel.NoteUiState
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, noteUiState: NoteUiState = NoteUiState.Loading) {

    val context = LocalContext.current

    var searchNotesInputField by remember { mutableStateOf("") }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

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

                Column(modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(32.dp)) {

                    OutlinedTextField(
                        value = searchNotesInputField,
                        onValueChange = { searchNotesInputField = it },
                        label = { Text(text = "Search your notes") },
                        trailingIcon = { Icon(imageVector = Icons.Outlined.Splitscreen, contentDescription = null) },
                        shape = RoundedCornerShape(50),
                        modifier = modifier
                            .heightIn(min = 20.dp)
                            .fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1
                        )

                    NoteLazyColumn(notes = noteUiState.notes, searchText = searchNotesInputField)
                }

                FloatingActionButton(onClick = { AddNotesActivity.startActivity(context as Activity) },
                    modifier = modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomEnd)
                        .padding(16.dp)) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                }
            }
        }
    }
}

@Composable
private fun NoteLazyColumn(modifier: Modifier = Modifier, notes: List<Note> = emptyList(), searchText: String = "") {

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
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(notes) {
                if (it.text.startsWith(prefix = searchText, ignoreCase = true)) {
                    NoteCard(
                        note = it,
                        onNoteClick = {  selectedNote ->
                            EditNotesActivity.startActivity(activity = context as Activity, note = selectedNote)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun NoteCard(modifier: Modifier = Modifier,
                     note: Note = Note(text = ""),
                     onNoteClick: (Note) -> Unit =  { _, -> }) {
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(0.1.dp, color = Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = { onNoteClick(note) }
        ) {
        Text(text = note.text,
            modifier = modifier.padding(8.dp),
            fontSize = 20.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenLightThemePreview() {
    NotesAppTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenDarkThemePreview() {
    NotesAppTheme(darkTheme = true) {
        HomeScreen()
    }
}