package com.embarkapps.inscribe.notes.presentation.noteslist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.embarkapps.inscribe.notes.presentation.NotesState
import com.embarkapps.inscribe.notes.presentation.NotesUiEvent
import com.embarkapps.inscribe.notes.presentation.noteslist.components.NoteCard
import com.embarkapps.inscribe.notes.presentation.noteslist.components.previewNote
import com.example.compose.InscribeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesListScreen(
    state: NotesState,
    onEvent: (NotesUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(NotesUiEvent.OnAddNoteClicked) }) {
                Icon(imageVector = Icons.Default.Edit, "Create new note")
            }
        },
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        LazyVerticalStaggeredGrid(
            modifier = modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.notes) { note ->
                NoteCard(
                    note = note,
                    onClick = {
                        onEvent(NotesUiEvent.OnNoteClicked(note))
                    }
                )
            }

        }
    }
}

@PreviewLightDark
@Composable
fun NotesListScreenPreview(modifier: Modifier = Modifier) {
    InscribeTheme {
        NotesListScreen(
            state = NotesState(
                isLoading = false,
                notes = (1..100).map {
                    previewNote.copy()
                }),
            onEvent = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}