package com.codermp.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codermp.core.presentation.designsystem.RunFitTheme

/**
 * Composable function that creates a custom scaffold.
 * @param modifier The [Modifier] to be applied to the scaffold.
 * @param withGradient Whether or not to apply a gradient background.
 * @param topAppBar The top app bar to be displayed.
 * @param floatingActionButton The floating action button to be displayed.
 * @param content The Composable content to be displayed.
 */
@Composable
fun RunFitScaffold(
    modifier: Modifier = Modifier,
    withGradient: Boolean = true,
    topAppBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topAppBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) { padding ->
        if(withGradient) {
            GradientBackground {
                content(padding)
            }
        } else {
            content(padding)
        }
    }
}

@Preview
@Composable
private fun PreviewRunFitScaffold() {
    RunFitTheme {
        RunFitScaffold {  }
    }
}