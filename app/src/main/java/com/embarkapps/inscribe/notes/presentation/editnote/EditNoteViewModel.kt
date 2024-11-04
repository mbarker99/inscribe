package com.embarkapps.inscribe.notes.presentation.editnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.inscribe.notes.domain.local.LocalStorageRepository
import com.embarkapps.inscribe.notes.domain.model.Note
import com.embarkapps.inscribe.notes.presentation.NotesUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject internal constructor(
    private val localRepository: LocalStorageRepository
) : ViewModel() {
    private val _state = MutableStateFlow(EditNoteState())
    val state = _state
        .onStart { loadSelectedCoin() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EditNoteState()
        )

    fun eventHandler(notesUiEvent: NotesUiEvent) {
        viewModelScope.launch {
            when (notesUiEvent) {
                is NotesUiEvent.OnNoteTitleChanged -> {
                    _state.update {
                        it.copy(
                            title = notesUiEvent.title
                        )
                    }
                }

                is NotesUiEvent.OnNoteContentChanged -> {
                    _state.update {
                        it.copy(
                            content = notesUiEvent.content
                        )
                    }
                }

                is NotesUiEvent.OnNoteSaved -> {
                    saveNote(notesUiEvent.note)
                }
                else -> {}
            }
        }
    }

    private fun saveNote(note: Note) {
        viewModelScope.launch {
            localRepository.insertAll(note)
        }
    }

    private fun loadSelectedCoin() {
        /*  if (_state.value.selectedNoteId == -1 && _state.value.selectedNote == null) {
              Log.d("EDITNOTEVIEWMODEL", "It should show a new note here.")
          } else {
              viewModelScope.launch {
                  _state.update { it.copy(isLoading = true) }

                  localRepository.getNoteById(id)
                      .flowOn(Dispatchers.IO)
                      .collect { note ->
                          _state.update { it.copy(selectedNote = note) }
                      }
              }
          }*/
    }
}