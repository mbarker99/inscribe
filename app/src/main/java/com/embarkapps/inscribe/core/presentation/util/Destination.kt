package com.embarkapps.inscribe.core.presentation.util

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object NotesGraph : Destination

    @Serializable
    data object NotesListDestination : Destination

    @Serializable
    data class EditNoteDestination(val id: String) : Destination
}