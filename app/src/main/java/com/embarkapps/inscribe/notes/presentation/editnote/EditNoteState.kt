package com.embarkapps.inscribe.notes.presentation.editnote

import com.embarkapps.inscribe.notes.domain.model.Note

data class EditNoteState(
    val isLoading: Boolean = false,
    val selectedNoteId: Int = -1,
    val selectedNote: Note? = null,
    val isChanged: Boolean = false
)
