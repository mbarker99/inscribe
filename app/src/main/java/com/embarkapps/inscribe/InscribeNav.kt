package com.embarkapps.inscribe

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.embarkapps.inscribe.common.Routes.SIGN_IN_SCREEN
import com.embarkapps.inscribe.presentation.signin.SignInScreen

@Composable
fun InscribeNav() {
    Surface(color = MaterialTheme.colorScheme.background) {
        val appState = rememberAppState()
        Scaffold { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = SIGN_IN_SCREEN,
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
    composable(SIGN_IN_SCREEN) {
        SignInScreen()
    }
}
