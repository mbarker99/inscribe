package com.embarkapps.inscribe.notes.data.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.embarkapps.inscribe.core.domain.Constants
import com.embarkapps.inscribe.notes.domain.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM ${Constants.NOTE_TABLE_NAME}")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM ${Constants.NOTE_TABLE_NAME} WHERE ${Constants.DB_ID} = :id")
    suspend fun getNoteByIndex(id: Int): Note

    @Upsert
    suspend fun upsert(note: Note)
}