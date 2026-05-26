package com.example.myapplication.data

import androidx.room.*

@Dao
interface JournalDao {
    @Insert
    suspend fun insertJournal(journal: Journal)

    @Update
    suspend fun updateJournal(journal: Journal)

    @Delete
    suspend fun deleteJournal(journal: Journal)

    @Query("SELECT * FROM journals ORDER BY timestamp DESC")
    suspend fun getAllJournals(): List<Journal>

    @Query("SELECT * FROM journals WHERE id = :id")
    suspend fun getJournalById(id: Int): Journal?
}
