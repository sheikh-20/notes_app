package com.application.notesapp.ui.settings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.notesapp.ui.theme.NotesAppTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Text(text = "Settings Screen")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SettingsScreenLightThemePreview() {
    NotesAppTheme(darkTheme = false) {
        SettingsScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SettingsScreenDarkThemePreview() {
    NotesAppTheme(darkTheme = true) {
        SettingsScreen()
    }
}