package com.embarkapps.inscribe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.embarkapps.inscribe.core.presentation.util.navigation.NavGraph
import com.embarkapps.inscribe.core.presentation.util.navigation.Navigator
import com.example.compose.InscribeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InscribeActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            InscribeTheme {
                NavGraph(navigator)
            }
        }
    }
}
