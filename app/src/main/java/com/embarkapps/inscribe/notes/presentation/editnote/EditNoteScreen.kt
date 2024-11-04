package com.embarkapps.inscribe.notes.presentation.editnote

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.embarkapps.inscribe.notes.domain.model.Note
import com.embarkapps.inscribe.notes.presentation.NotesUiEvent
import com.embarkapps.inscribe.notes.presentation.noteslist.components.previewNote
import com.example.compose.InscribeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditNoteScreen(
    state: EditNoteState,
    onEvent: (NotesUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { },
                modifier = Modifier,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            TextField(
                value = state.title,
                onValueChange = { onEvent(NotesUiEvent.OnNoteTitleChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = state.content,
                onValueChange = { onEvent(NotesUiEvent.OnNoteContentChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {
                onEvent(
                    NotesUiEvent.OnNoteSaved(
                        Note(
                            title = state.title,
                            content = state.content
                        )
                    )
                )
            }
            ) {
                Text(text = "Save")
            }
        }
    }
}

@PreviewLightDark
@Composable
fun EditNoteScreenPreview(modifier: Modifier = Modifier) {
    InscribeTheme {
        EditNoteScreen(
            state = EditNoteState(
                title = previewNote.title,
                content = previewNote.content
            ),
            onEvent = { },
            modifier = Modifier
        )
    }

}