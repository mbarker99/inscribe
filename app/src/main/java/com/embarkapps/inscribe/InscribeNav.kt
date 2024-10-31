package com.embarkapps.inscribe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.embarkapps.inscribe.core.presentation.util.Destination
import com.embarkapps.inscribe.core.presentation.util.NavigationAction
import com.embarkapps.inscribe.core.presentation.util.Navigator
import com.embarkapps.inscribe.core.presentation.util.ObserveAsEvents
import com.embarkapps.inscribe.notes.presentation.editnote.EditNoteScreen
import com.embarkapps.inscribe.notes.presentation.editnote.EditNoteViewModel
import com.embarkapps.inscribe.notes.presentation.noteslist.NotesListScreen
import com.embarkapps.inscribe.notes.presentation.noteslist.NotesListViewModel

@Composable
fun InscribeNav(navigator: Navigator) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddingModifier ->
        val navController = rememberNavController()

        ObserveAsEvents(flow = navigator.navigationActions) { action ->
            when (action) {
                is NavigationAction.Navigate -> navController.navigate(action.destination) {
                    action.navOptions(this)
                }

                NavigationAction.NavigateUp -> navController.navigateUp()
            }
        }

        NavHost(
            navController = navController,
            startDestination = navigator.startDestination,
            modifier = Modifier.padding(innerPaddingModifier)
        ) {
            navigation<Destination.NotesGraph>(startDestination = Destination.NotesListDestination) {
                composable<Destination.NotesListDestination> {
                    val viewModel = hiltViewModel<NotesListViewModel>()
                    NotesListScreen(
                        state = viewModel.state.collectAsStateWithLifecycle().value,
                        onEvent = viewModel::eventHandler,
                        modifier = Modifier
                    )
                }
                composable<Destination.EditNoteDestination> {
                    val viewModel = hiltViewModel<EditNoteViewModel>()
                    EditNoteScreen(
                        state = viewModel.state.collectAsStateWithLifecycle().value,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
