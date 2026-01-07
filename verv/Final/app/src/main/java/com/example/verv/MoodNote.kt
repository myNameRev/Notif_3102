package com.example.verv

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_table")
data class MoodNote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val emoji: String,
    val date: String,
    var isFavorite: Boolean = false //
)