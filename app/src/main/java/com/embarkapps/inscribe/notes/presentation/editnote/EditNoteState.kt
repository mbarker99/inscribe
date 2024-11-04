package com.embarkapps.inscribe.notes.presentation.editnote

data class EditNoteState(
    val isLoading: Boolean = false,
    val selectedNoteId: Int = -1,
    val title: String = "",
    val content: String = "",
    val isChanged: Boolean = false
)
