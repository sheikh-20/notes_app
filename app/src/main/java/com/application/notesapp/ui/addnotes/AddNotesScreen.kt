package com.application.notesapp.ui.addnotes

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Done
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.notesapp.ui.theme.NotesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotesScreen(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    val focusManager = LocalFocusManager.current

    var inputField by remember { mutableStateOf("") }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Column(modifier = modifier.fillMaxSize()) {

            OutlinedTextField(
                value = inputField,
                onValueChange = { inputField = it },
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20),
                label = { Text(text = "Enter notes") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            )
        }

        FloatingActionButton(
            onClick = onClick,
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Done, contentDescription = null)
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