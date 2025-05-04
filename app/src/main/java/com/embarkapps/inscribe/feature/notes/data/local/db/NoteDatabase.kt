package com.embarkapps.inscribe.feature.notes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.embarkapps.inscribe.feature.notes.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}