package com.application.notesapp.ui.addnotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.application.notesapp.data.localdb.Note
import com.application.notesapp.ui.theme.NotesAppTheme
import com.application.notesapp.ui.viewmodel.NoteViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNotesActivity : ComponentActivity() {

    companion object {
        const val NOTE = "note"

        fun startActivity(activity: Activity?, note: Note? = null) {
            val intent = Intent(activity, AddNotesActivity::class.java)
            note?.let { intent.putExtra(NOTE, Gson().toJson(it)) }
            activity?.startActivity(intent)
        }
    }

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra(NOTE)?.let {
            val selectedNote = Gson().fromJson(it, Note::class.java)
            viewModel.updateSelectedNote(selectedNote)
        }

        setContent {
            NotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AddNotesScreenApp()
                }
            }
        }
    }
}