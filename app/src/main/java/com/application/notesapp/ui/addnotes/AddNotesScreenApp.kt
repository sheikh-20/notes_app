package com.application.notesapp.ui.addnotes

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun AddNotesScreenApp(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    AddNotesScreen(onClick = { (context as Activity).finish() })
}