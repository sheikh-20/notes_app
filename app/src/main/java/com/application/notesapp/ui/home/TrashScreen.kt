package com.application.notesapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TrashScreen(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Trash Screen")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TrashScreenLightThemePreview() {
    TrashScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TrashScreenDarkThemePreview() {
    TrashScreen()
}