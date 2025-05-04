package com.embarkapps.inscribe.core.presentation.util.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.embarkapps.inscribe.feature.notes.presentation.editnote.EditNoteScreen
import com.embarkapps.inscribe.feature.notes.presentation.noteslist.NotesListScreen
import com.embarkapps.inscribe.feature.notes.presentation.noteslist.NotesListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraph(navigator: Navigator) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val viewModel = hiltViewModel<NotesListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        ObserveAsEvents(flow = navigator.navigationActions) { action ->
            when (action) {
                is NavigationAction.Navigate -> navController.navigate(action.destination) {
                    action.navOptions(this)
                    launchSingleTop = true
                }

                NavigationAction.NavigateUp -> navController.popBackStack()
            }
        }

        NavHost(
            navController = navController,
            startDestination = navigator.startDestination,
        ) {
            navigation<Destination.NotesGraph>(startDestination = Destination.NotesListDestination) {
                composable<Destination.NotesListDestination> {
                    Log.d("NavHost", "navigate to NotesList")
                    NotesListScreen(
                        state = state,
                        onEvent = viewModel::eventHandler,
                        modifier = Modifier
                    )
                }
                composable<Destination.EditNoteDestination> {
                    Log.d("NavHost", "navigate to EditNote")
                    EditNoteScreen(
                        state = state,
                        onEvent = viewModel::eventHandler,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
