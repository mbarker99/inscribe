package com.embarkapps.inscribe.notes.data.local

import com.embarkapps.inscribe.notes.data.local.db.NoteDatabase
import com.embarkapps.inscribe.notes.domain.local.LocalStorageRepository
import com.embarkapps.inscribe.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    private val db: NoteDatabase
) : LocalStorageRepository {
    override suspend fun getAllNotes(): Flow<List<Note>> = flow {
        emit(db.noteDao().getAllNotes())
        }

    override suspend fun getNoteById(id: Int): Flow<Note> = flow {
        emit(db.noteDao().getNoteByIndex(id))
    }

    override suspend fun insertAll(vararg notes: Note) = db.noteDao().insertAll(*notes)
}