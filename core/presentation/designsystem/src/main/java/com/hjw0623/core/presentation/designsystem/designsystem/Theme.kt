package com.hjw0623.core.presentation.designsystem.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = LostArkBlack,
    background = LostArkBlack,
    surface = LostArkDarkGray,
    secondary = LostArkDarkRed,
    tertiary = LostArkGray,
    primaryContainer = LostArkBlack30,
    onPrimary = LostArkWhite,
    onBackground = LostArkWhite,
    onSurface = LostArkWhite,
    onSurfaceVariant = LostArkGray40
)


@Composable
fun LostarkTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}