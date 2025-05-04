package com.embarkapps.inscribe.feature.notes.domain.local

import com.embarkapps.inscribe.feature.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {
    suspend fun getAllNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Flow<Note>
    suspend fun upsert(note: Note)
}