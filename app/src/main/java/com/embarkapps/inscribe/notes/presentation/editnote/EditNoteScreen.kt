package com.embarkapps.inscribe.notes.presentation.editnote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.inscribe.notes.domain.model.Note
import com.embarkapps.inscribe.notes.presentation.NotesState
import com.embarkapps.inscribe.notes.presentation.NotesUiAction
import com.embarkapps.inscribe.notes.presentation.noteslist.components.previewNote
import com.example.compose.InscribeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    state: NotesState,
    onEvent: (NotesUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val textFieldColors = TextFieldDefaults.colors().copy(
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        errorContainerColor = MaterialTheme.colorScheme.background,
        disabledContainerColor = MaterialTheme.colorScheme.background,
        focusedIndicatorColor = MaterialTheme.colorScheme.background,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
        disabledIndicatorColor = MaterialTheme.colorScheme.background,
        errorIndicatorColor = MaterialTheme.colorScheme.background,
    )

    val titleTextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    val emptyNote = Note()
    val note: Note = state.selectedNote ?: emptyNote
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onEvent(
                                    NotesUiAction.OnBackPressed(
                                        Note(
                                            id = note.id,
                                            title = note.title,
                                            content = note.content
                                        )
                                    )
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    title = { },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.Start,
            ) {
                TextField(
                    placeholder = {
                        Text(
                            text = "Title",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(0.dp)
                        )
                    },
                    textStyle = titleTextStyle,
                    value = note.title,
                    onValueChange = { onEvent(NotesUiAction.OnNoteTitleChanged(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                TextField(
                    placeholder = {
                        Text(
                            text = "Note",
                            modifier = Modifier.padding(0.dp),
                        )
                    },
                    value = note.content,
                    onValueChange = { onEvent(NotesUiAction.OnNoteContentChanged(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )
            }
        }
    }



}

@PreviewLightDark
@Composable
fun EditNoteScreenPreview(modifier: Modifier = Modifier) {
    InscribeTheme {
        EditNoteScreen(
            state = NotesState(
                selectedNote = previewNote
            ),
            onEvent = { },
            modifier = Modifier
        )
    }

}

