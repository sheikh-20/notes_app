package com.application.notesapp.ui.addnotes

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.notesapp.ui.theme.NotesAppTheme
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotesScreen(modifier: Modifier = Modifier,
                   selectedTitle: String = "",
                   selectedNote: String = "",
                   onUpdateClick: (String, String) -> Unit = { _, _ -> },
                   onSaveClick: (String, String) -> Unit = { _, _ -> }) {

    val focusManager = LocalFocusManager.current

    val titleInteractionSource = remember { MutableInteractionSource() }
    val titlePressedState by titleInteractionSource.collectIsFocusedAsState()

    val notesInteractionSource = remember { MutableInteractionSource() }
    val notesPressedState by notesInteractionSource.collectIsFocusedAsState()

    var titleInputField by remember { mutableStateOf(selectedTitle) }
    var notesInputField by remember { mutableStateOf(selectedNote) }

    var updateState by remember { mutableStateOf(false) }

    if (selectedTitle.isNotEmpty() || selectedNote.isNotEmpty()) { updateState = true }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {

            BasicTextField(
                value = titleInputField,
                onValueChange = { titleInputField = it },
                modifier = modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                cursorBrush = SolidColor(Color.White),
                decorationBox = {
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                        if (titleInputField.isEmpty() && !titlePressedState) {
                            Text(text = "Enter title", color = Color.LightGray)
                        }
                        it()
                    }
                },
                interactionSource = titleInteractionSource
            )

            BasicTextField(
                value = notesInputField,
                onValueChange = { notesInputField = it },
                modifier = modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.None),
                cursorBrush = SolidColor(Color.White),
                decorationBox = {
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                        if (notesInputField.isEmpty() && !notesPressedState) {
                            Text(text = "Enter notes", color = Color.LightGray)
                        }
                        it()
                    }
                },
                interactionSource = notesInteractionSource
            )
        }

        FloatingActionButton(
            onClick = {
                if (updateState) onUpdateClick(titleInputField, notesInputField) else onSaveClick(titleInputField, notesInputField) },
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = if (updateState) Icons.Outlined.Edit else Icons.Outlined.Done, contentDescription = null)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AddNotesScreenLightThemePreview() {
    NotesAppTheme(darkTheme = false) {
        AddNotesScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AddNotesScreenDarkThemePreview() {
    NotesAppTheme(darkTheme = true) {
        AddNotesScreen()
    }
}