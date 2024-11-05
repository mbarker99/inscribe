package com.embarkapps.inscribe.notes.presentation

import com.embarkapps.inscribe.notes.domain.model.Note

sealed interface NotesUiEvent {
    // Notes List
    data object OnAddNoteClicked : NotesUiEvent
    data class OnNoteClicked(val id: Int) : NotesUiEvent

    // Edit Note
    data class OnNoteTitleChanged(val title: String) : NotesUiEvent
    data class OnNoteContentChanged(val content: String) : NotesUiEvent
    data class OnNoteSaved(val note: Note) : NotesUiEvent
}