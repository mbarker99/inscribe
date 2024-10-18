package com.embarkapps.inscribe.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.embarkapps.inscribe.common.Constants
import com.embarkapps.inscribe.notes.domain.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM ${Constants.NOTE_TABLE_NAME}")
    fun getAllNotes(): List<Note>

    @Upsert
    suspend fun insertAll(vararg notes: Note)
}