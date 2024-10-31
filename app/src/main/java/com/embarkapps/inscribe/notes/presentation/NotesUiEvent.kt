package com.embarkapps.inscribe.notes.presentation

sealed interface NotesUiEvent {
    data object OnNewNoteClicked : NotesUiEvent
}