package com.application.notesapp.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.notesapp.ui.theme.NotesAppTheme

@Composable
fun ReminderScreen(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Reminder Screen", style = MaterialTheme.typography.displayLarge)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ReminderScreenLightThemePreview() {
    NotesAppTheme(darkTheme = false) {
        ReminderScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ReminderScreenDarkThemePreview() {
    NotesAppTheme(darkTheme = true) {
        ReminderScreen()
    }
}