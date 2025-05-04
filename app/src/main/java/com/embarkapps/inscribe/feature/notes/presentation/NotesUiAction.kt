package com.embarkapps.inscribe.feature.notes.presentation

import com.embarkapps.inscribe.feature.notes.domain.model.Note

sealed interface NotesUiAction {
    // Notes List
    data object OnAddNoteClicked : NotesUiAction
    data class OnNoteClicked(val selectedNote: Note) : NotesUiAction

    // Edit Note
    data class OnNoteTitleChanged(val title: String) : NotesUiAction
    data class OnNoteContentChanged(val content: String) : NotesUiAction
    data class OnBackPressed(val note: Note) : NotesUiAction
}