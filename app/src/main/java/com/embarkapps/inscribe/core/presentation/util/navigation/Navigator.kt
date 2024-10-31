package com.embarkapps.inscribe.core.presentation.util.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow

interface Navigator {
    val startDestination: Destination
    val navigationActions: Flow<NavigationAction>

    suspend fun navigate(destination: Destination, navOptions: NavOptionsBuilder.() -> Unit = {})
    suspend fun navigateUp()
}