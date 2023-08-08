package com.application.notesapp.data.localdb

import android.os.Parcelable
import android.text.format.DateUtils
import android.util.TimeUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val updatedDate: Long? = Date().time
)
