package com.embarkapps.inscribe.feature.notes.presentation

import com.embarkapps.inscribe.feature.notes.domain.model.Note

data class NotesState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val selectedNote: Note? = null
)
