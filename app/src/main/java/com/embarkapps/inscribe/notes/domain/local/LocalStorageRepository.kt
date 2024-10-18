package com.embarkapps.inscribe.notes.domain.local

import com.embarkapps.inscribe.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun insertAll(vararg notes: Note): Flow<Unit>
}