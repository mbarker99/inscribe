package com.embarkapps.inscribe.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.embarkapps.inscribe.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}