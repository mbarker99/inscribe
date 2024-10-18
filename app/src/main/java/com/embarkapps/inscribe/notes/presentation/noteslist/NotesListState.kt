package com.embarkapps.inscribe.notes.presentation.noteslist

import com.embarkapps.inscribe.notes.domain.model.Note

data class NotesListState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList()
)
