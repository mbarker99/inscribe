package com.embarkapps.inscribe.presentation.noteslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.inscribe.domain.local.LocalStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val state = _state.asStateFlow()

    fun onNewNoteClicked() {

    }
}