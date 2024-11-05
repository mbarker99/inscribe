package com.embarkapps.inscribe.notes.presentation.editnote

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.inscribe.core.presentation.util.navigation.Navigator
import com.embarkapps.inscribe.notes.domain.local.LocalStorageRepository
import com.embarkapps.inscribe.notes.domain.model.Note
import com.embarkapps.inscribe.notes.presentation.NotesUiEvent
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
class EditNoteViewModel @Inject internal constructor(
    private val localRepository: LocalStorageRepository,
    private val navigator: Navigator
) : ViewModel() {
    private val _state = MutableStateFlow(EditNoteState())
    val state = _state
        .onStart { loadSelectedCoin(_state.value.selectedNoteId) }
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
        if (note.title.isNotEmpty() && note.content.isNotEmpty())
        viewModelScope.launch {
            localRepository.insertAll(note)
            navigator.navigateUp()
            Log.d("EditNoteViewModel", "saveNote(${note})")
        } else {
            Log.d("EditNoteViewModel", "Note empty. Not saved.")
        }
    }

    private fun loadSelectedCoin(id: Int) {
        Log.d("EditNoteViewModel", "selectedNoteId: $id")
        if (_state.value.selectedNoteId != -1) {
            viewModelScope.launch {
                localRepository.getNoteById(id)
                    .flowOn(Dispatchers.IO)
                    .collect { note ->
                        _state.update {
                            it.copy(
                                title = note.title,
                                content = note.content
                            )
                        }
                    }
            }
        }
    }
}