package com.application.notesapp.ui.editnotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.application.notesapp.data.Note
import com.application.notesapp.ui.theme.NotesAppTheme
import com.google.gson.Gson
import timber.log.Timber

class EditNotesActivity: ComponentActivity() {

    companion object {
        const val NOTE = "note"
        private const val TAG = "EditNotesActivity"

        fun startActivity(activity: Activity?, note: Note?) {
            val intent = Intent(activity, EditNotesActivity::class.java)
            intent.putExtra(NOTE, Gson().toJson(note))
            activity?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesAppTheme {
                val gson = Gson().fromJson(intent.getStringExtra(NOTE), Note::class.java)
                Timber.tag(TAG).d(gson.text)
                Text(text = "Hello World")
            }
        }
    }
}