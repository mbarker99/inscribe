package com.embarkapps.inscribe.notes.presentation.editnote

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject internal constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(EditNoteState())
    val state = _state
}