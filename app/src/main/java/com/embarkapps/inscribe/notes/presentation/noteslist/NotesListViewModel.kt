package com.embarkapps.inscribe.notes.presentation.noteslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.inscribe.notes.domain.local.LocalStorageRepository
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
    private val localRepository: LocalStorageRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            //localRepository.getAllNotes()
        }
    }

    private val _state = MutableStateFlow(NotesListState())
    val state = _state
        .onStart { loadNotes() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            NotesListState()
        )

    private fun loadNotes() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            localRepository.getAllNotes()
                .flowOn(Dispatchers.IO)
                .collect { notes ->
                    _state.update {
                        Log.d("TAG", "state updated: notes=$notes")
                        it.copy(notes = notes)
                    }
                }
        }
    }

    fun onNewNoteClicked() {

    }

    fun onAction(notesListAction: NotesListAction) {

    }
}