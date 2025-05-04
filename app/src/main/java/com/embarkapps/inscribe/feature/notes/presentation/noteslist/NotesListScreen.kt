package com.embarkapps.inscribe.feature.notes.presentation.noteslist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.embarkapps.inscribe.feature.notes.presentation.NotesState
import com.embarkapps.inscribe.feature.notes.presentation.NotesUiAction
import com.embarkapps.inscribe.feature.notes.presentation.noteslist.components.NavDrawer
import com.embarkapps.inscribe.feature.notes.presentation.noteslist.components.NoteCard
import com.embarkapps.inscribe.feature.notes.presentation.noteslist.components.previewNote
import com.example.compose.InscribeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesListScreen(
    state: NotesState,
    onEvent: (NotesUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val navDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = { NavDrawer(navDrawerState) },
            drawerState = navDrawerState,
            gesturesEnabled = true,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = " Notes") },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        navDrawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Menu, "Menu")
                            }
                        },
                        actions = {
                            Icon(Icons.Default.Search, "Search")
                            Spacer(modifier = modifier.width(16.dp))
                            Icon(Icons.Default.MoreVert, "Overflow")
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { onEvent(NotesUiAction.OnAddNoteClicked) }) {
                        Icon(imageVector = Icons.Default.Edit, "Create new note")
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp),
            ) { paddingValues ->
                LazyVerticalStaggeredGrid(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.notes) { note ->
                        NoteCard(
                            note = note,
                            onClick = {
                                onEvent(NotesUiAction.OnNoteClicked(note))
                            }
                        )
                    }

                }

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