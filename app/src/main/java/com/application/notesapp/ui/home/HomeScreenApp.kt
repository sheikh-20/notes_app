package com.application.notesapp.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.notesapp.ui.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenApp(modifier: Modifier = Modifier, viewModel: NoteViewModel = hiltViewModel()) {

   val noteUiState by viewModel.noteUiState.collectAsState()

   Scaffold(topBar = {
//      TopAppBar(title = { Text(text = "Notes App") })
   }) { paddingValues ->
      HomeScreen(modifier = modifier.padding(paddingValues), noteUiState = noteUiState)
   }
}