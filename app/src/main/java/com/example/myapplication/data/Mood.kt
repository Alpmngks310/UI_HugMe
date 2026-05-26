package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class Mood(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val moodIcon: String,
    val timestamp: Long = System.currentTimeMillis()
)
