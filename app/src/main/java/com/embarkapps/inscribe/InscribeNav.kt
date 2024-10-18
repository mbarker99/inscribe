package com.embarkapps.inscribe

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.embarkapps.inscribe.common.Screen
import com.embarkapps.inscribe.notes.presentation.noteslist.NotesListScreen
import com.embarkapps.inscribe.notes.presentation.noteslist.NotesListViewModel

@Composable
fun InscribeNav() {
    Surface(color = MaterialTheme.colorScheme.background) {
        val appState = rememberAppState()
        Scaffold { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = Screen.SignInScreen.route,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                inscribeGraph(appState)
            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        InscribeState(navController)
    }

fun NavGraphBuilder.inscribeGraph(appState: InscribeState) {
    composable(route = Screen.SignInScreen.route) {
        val viewModel = hiltViewModel<NotesListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        NotesListScreen(
            state,
            onAction = viewModel::onAction,
            modifier = Modifier
        )
    }
}
