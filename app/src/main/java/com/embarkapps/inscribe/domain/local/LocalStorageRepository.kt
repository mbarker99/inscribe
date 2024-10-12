package com.embarkapps.inscribe.domain.local

import com.embarkapps.inscribe.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {
    suspend fun getAllNotes(): Flow<List<Note>>
    suspend fun insertAll(vararg notes: Note): Flow<Unit>
}