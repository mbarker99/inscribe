package com.embarkapps.inscribe.notes.presentation.editnote

import com.embarkapps.inscribe.notes.domain.model.Note

data class EditNoteState(
    val selectedNote: Note? = null,
    val isChanged: Boolean = false
)
