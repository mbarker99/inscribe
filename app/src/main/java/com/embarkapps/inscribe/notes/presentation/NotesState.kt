package com.embarkapps.inscribe.notes.presentation

import com.embarkapps.inscribe.notes.domain.model.Note

data class NotesState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val selectedNote: Note? = null
)
