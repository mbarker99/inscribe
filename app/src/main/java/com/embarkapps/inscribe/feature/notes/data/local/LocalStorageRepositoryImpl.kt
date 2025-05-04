package com.embarkapps.inscribe.feature.notes.data.local

import com.embarkapps.inscribe.feature.notes.data.local.db.NoteDatabase
import com.embarkapps.inscribe.feature.notes.domain.local.LocalStorageRepository
import com.embarkapps.inscribe.feature.notes.domain.model.Note
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

    override suspend fun upsert(note: Note) = db.noteDao().upsert(note)
}