package com.embarkapps.inscribe.notes.presentation.editnote

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.embarkapps.inscribe.notes.presentation.NotesUiEvent
import com.embarkapps.inscribe.notes.presentation.noteslist.components.previewNote
import com.example.compose.InscribeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditNoteScreen(
    state: EditNoteState,
    onEvent: (NotesUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.selectedNote?.title ?: "",
                onValueChange = { onEvent(NotesUiEvent.OnNoteTitleChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = state.selectedNote?.content ?: "",
                onValueChange = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@PreviewLightDark
@Composable
fun EditNoteScreenPreview(modifier: Modifier = Modifier) {
    InscribeTheme {
        EditNoteScreen(
            state = EditNoteState(
                selectedNote = previewNote
            ),
            onEvent = { },
            modifier = Modifier
        )
    }

}