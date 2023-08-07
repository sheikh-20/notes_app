package com.application.notesapp.ui.home

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.notesapp.ui.addnotes.AddNotesActivity
import com.application.notesapp.ui.theme.NotesAppTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

        FloatingActionButton(onClick = { AddNotesActivity.startActivity(context as Activity) },
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomEnd).padding(16.dp)) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
        }
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