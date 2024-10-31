package com.embarkapps.inscribe.notes.presentation

import com.embarkapps.inscribe.notes.domain.model.Note

sealed interface NotesUiEvent {
    // Notes List
    data object OnAddNoteClicked : NotesUiEvent
    data class OnNoteClicked(val note: Note) : NotesUiEvent

    // Edit Note
    data class OnNoteTitleChanged(val title: String) : NotesUiEvent
    data class OnNoteContentChanged(val content: String) : NotesUiEvent
}