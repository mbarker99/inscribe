package com.embarkapps.inscribe.presentation.noteslist

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(

) : ViewModel() {
    init {
        Log.d("TAG", "NotesListViewModel init")
    }
}