package com.embarkapps.inscribe.presentation.noteslist

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onNewNoteClicked() }) {
                Icon(imageVector = Icons.Default.Edit, "Create new note")
            }
        }) {
        LazyColumn {

        }
    }
}