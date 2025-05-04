package com.embarkapps.inscribe.notes.presentation.noteslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.inscribe.core.presentation.util.navigation.Destination
import com.embarkapps.inscribe.core.presentation.util.navigation.Navigator
import com.embarkapps.inscribe.notes.domain.local.LocalStorageRepository
import com.embarkapps.inscribe.notes.domain.model.Note
import com.embarkapps.inscribe.notes.presentation.NotesState
import com.embarkapps.inscribe.notes.presentation.NotesUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject internal constructor(
    private val localRepository: LocalStorageRepository,
    private val navigator: Navigator
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state
        .onStart { loadNotes() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            NotesState()
        )

    private fun loadNotes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            localRepository.getAllNotes()
                .flowOn(Dispatchers.IO)
                .collect { notes ->
                    _state.update { it.copy(isLoading = false, notes = notes) }
                }
        }
    }

    fun eventHandler(notesUiAction: NotesUiAction) {
        viewModelScope.launch {
            when (notesUiAction) {
                NotesUiAction.OnAddNoteClicked -> {
                    _state.update { it.copy(selectedNote = Note()) }
                    navigator.navigate(Destination.EditNoteDestination)
                }

                is NotesUiAction.OnNoteClicked -> {
                    _state.update { it.copy(selectedNote = notesUiAction.selectedNote) }
                    navigator.navigate(Destination.EditNoteDestination)
                }

                is NotesUiAction.OnNoteTitleChanged -> {
                    _state.update {
                        it.copy(
                            selectedNote = state.value.selectedNote?.copy(title = notesUiAction.title)
                        )
                    }
                }

                is NotesUiAction.OnNoteContentChanged -> {
                    _state.update {
                        it.copy(
                            selectedNote = state.value.selectedNote?.copy(content = notesUiAction.content)
                        )
                    }
                }

                is NotesUiAction.OnBackPressed -> {
                    saveNote(notesUiAction.note)
                    _state.update { it.copy(selectedNote = null) }
                    navigator.navigateUp()
                }
            }
        }
    }

    private fun saveNote(note: Note) {
        viewModelScope.launch {
            if (note.title.isNotEmpty() && note.content.isNotEmpty()) {
                localRepository.upsert(note)
                Log.d("EditNoteViewModel", "saveNote(${note})")
                loadNotes()
            } else {
                Log.d("EditNoteViewModel", "Note empty. Not saved.")
            }
        }
    }
}
