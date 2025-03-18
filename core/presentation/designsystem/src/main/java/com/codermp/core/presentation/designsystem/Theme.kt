package com.codermp.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Dark color scheme for the app.
 */
val DarkColorScheme = darkColorScheme(
    primary = RunFitGreen,
    background = RunFitBlack,
    surface = RunFitDarkGray,
    secondary = RunFitWhite,
    tertiary = RunFitWhite,
    primaryContainer = RunFitGreen30,
    onPrimary = RunFitBlack,
    onBackground = RunFitWhite,
    onSurface = RunFitWhite,
    onSurfaceVariant = RunFitGray,
    error = RunFitDarkRed,
    errorContainer = RunFitDarkRed5
)

/**
 * Composable function that sets the theme for the app.
 * @param content The [Composable] content to be displayed.
 */
@Composable
fun RunFitTheme(
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