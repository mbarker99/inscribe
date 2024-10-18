package com.embarkapps.inscribe.notes.presentation.noteslist

sealed interface NotesListAction {
    data object onNewNoteClicked : NotesListAction
}