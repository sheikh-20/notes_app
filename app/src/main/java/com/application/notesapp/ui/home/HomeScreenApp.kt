package com.application.notesapp.ui.home

import android.app.Activity
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Splitscreen
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.notesapp.ui.addnotes.AddNotesActivity
import com.application.notesapp.ui.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenApp(modifier: Modifier = Modifier, viewModel: NoteViewModel = hiltViewModel()) {

   val context = LocalContext.current
   val noteUiState by viewModel.noteUiState.collectAsState()

   val layoutUiState by viewModel.layoutUiState.collectAsState()

   var searchNotesInputField by remember { mutableStateOf("") }

   Scaffold(
      topBar = { CustomTopAppBar(
         searchNotesValue = searchNotesInputField,
         onSearchNotesInputField = { searchNotesInputField  = it },
         isGridLayout = layoutUiState.isGridLayout,
         onChangeLayout = viewModel::updateLayoutPreference
         )  },

      floatingActionButton = {
         FloatingActionButton(onClick = { AddNotesActivity.startActivity(context as Activity) }, modifier = modifier.padding(16.dp)) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
      } },
      floatingActionButtonPosition = FabPosition.End
      ) { paddingValues ->
      HomeScreen(
         modifier = modifier.padding(paddingValues),
         noteUiState = noteUiState,
         isGridLayout = layoutUiState.isGridLayout,
         searchNotesInputField = searchNotesInputField,
         onDeleteNote = viewModel::deleteNotes)
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomTopAppBar(modifier: Modifier = Modifier,
                            searchNotesValue: String = "",
                            onSearchNotesInputField: (String) -> Unit = {},
                            isGridLayout: Boolean = true,
                            onChangeLayout: (Boolean) -> Unit = {}
                            ) {

   val interactionSource = remember { MutableInteractionSource() }

   CenterAlignedTopAppBar(title = {
         BasicTextField(value = searchNotesValue, onValueChange = onSearchNotesInputField, modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
            singleLine = true,
            maxLines = 1) {
            TextFieldDefaults.TextFieldDecorationBox(
               value = searchNotesValue,
               innerTextField = it,
               enabled = true,
               singleLine = true,
               visualTransformation = VisualTransformation.None,
               interactionSource = interactionSource,
               contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                  top = 0.dp,
                  bottom = 0.dp
               ),
               colors = TextFieldDefaults.textFieldColors(
                  textColor = Color.White,
                  disabledTextColor = Color.Transparent,
                  focusedIndicatorColor = Color.Transparent,
                  unfocusedIndicatorColor = Color.Transparent,
                  disabledIndicatorColor = Color.Transparent,
                  containerColor = Color(0xFF2b2827)
               ),
               shape = RoundedCornerShape(50),
               trailingIcon = {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                     IconButton(onClick = { onChangeLayout(isGridLayout.not()) }) {
                        Icon(imageVector = if (isGridLayout) Icons.Outlined.Splitscreen else Icons.Outlined.GridView, contentDescription = null) }

                     IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Outlined.ManageAccounts, contentDescription = null) }
                  }
               },
               leadingIcon = {
                             IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
                             }
               },

               placeholder = { Text(text = "Search your notes") }
            )
         }
   })
}