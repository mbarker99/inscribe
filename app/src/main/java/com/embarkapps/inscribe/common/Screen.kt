package com.embarkapps.inscribe.common

sealed class Screen(val route: String) {
    data object SignInScreen : Screen("sign_in_screen")
}