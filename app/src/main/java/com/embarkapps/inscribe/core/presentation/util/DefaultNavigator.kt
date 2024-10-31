package com.embarkapps.inscribe.core.presentation.util

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class DefaultNavigator(override val startDestination: Destination) : Navigator {
    private val _navigationActions = Channel<NavigationAction>()
    override val navigationActions = _navigationActions.receiveAsFlow()

    override suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit
    ) {
        _navigationActions.send(NavigationAction.Navigate(destination, navOptions))
    }

    override suspend fun navigateUp() {
        _navigationActions.send((NavigationAction.NavigateUp))
    }
}