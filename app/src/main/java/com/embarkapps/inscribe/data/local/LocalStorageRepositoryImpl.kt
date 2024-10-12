package com.embarkapps.inscribe.data.local

import com.embarkapps.inscribe.db.NoteDatabase
import com.embarkapps.inscribe.domain.local.LocalStorageRepository
import com.embarkapps.inscribe.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    private val db: NoteDatabase
) : LocalStorageRepository {
    override suspend fun getAllNotes(): Flow<List<Note>> =
        flow {
            db.noteDao().getAllNotes()
        }

    override suspend fun insertAll(vararg notes: Note): Flow<Unit> =
        flow {
            db.noteDao().insertAll()
        }
}