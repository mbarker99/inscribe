package com.embarkapps.inscribe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.compose.InscribeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InscribeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            InscribeTheme {
                InscribeNav()
            }
        }
    }
}
