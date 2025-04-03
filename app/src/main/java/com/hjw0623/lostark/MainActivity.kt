package com.hjw0623.lostark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hjw0623.core.presentation.designsystem.LostarkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { false } }
        enableEdgeToEdge()
        setContent {
            LostarkTheme {
                MainScreen()
            }
        }
    }
}

