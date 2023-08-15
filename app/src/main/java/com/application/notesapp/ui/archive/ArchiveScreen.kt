package com.application.notesapp.ui.archive

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.notesapp.ui.theme.NotesAppTheme

@Composable
fun ArchiveScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Archive Screen")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArchiveScreenLightThemePreview() {
    NotesAppTheme(darkTheme = false) {
        ArchiveScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArchiveScreenDarkThemePreview() {
    NotesAppTheme(darkTheme = true) {
        ArchiveScreen()
    }
}