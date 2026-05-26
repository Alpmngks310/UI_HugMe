package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoodDao {
    @Insert
    suspend fun insertMood(mood: Mood)

    @Query("SELECT * FROM moods ORDER BY timestamp DESC")
    suspend fun getAllMoods(): List<Mood>
}
