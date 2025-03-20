package com.codermp.auth.presentation.intro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codermp.auth.presentation.R
import com.codermp.core.presentation.designsystem.LogoIcon
import com.codermp.core.presentation.designsystem.RunFitTheme
import com.codermp.core.presentation.designsystem.components.GradientBackground
import com.codermp.core.presentation.designsystem.components.RunFitActionButton
import com.codermp.core.presentation.designsystem.components.RunFitOutlinedActionButton

/**
 * Composable function that defines the root screen for the introduction screen.
 * @param onSignUpClick The action to be performed when the sign up button is clicked.
 * @param onSignInClick The action to be performed when the sign in button is clicked.
 */
@Composable
fun IntroScreenRoot(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    IntroScreen(
        onAction = { action ->
            when (action) {
                IntroAction.OnSignInClick -> onSignInClick()
                IntroAction.OnSignUpClick -> onSignUpClick()
            }
        }
    )
}

/**
 * Composable function that defines the introduction screen.
 * @param onAction The action to be performed when a specific action is triggered.
 */
@Composable
fun IntroScreen(
    onAction: (IntroAction) -> Unit
) {
    GradientBackground {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            RunFitLogoVertical()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 48.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_to_runfit),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.runfit_description),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(32.dp))
            RunFitOutlinedActionButton(
                text = stringResource(R.string.sign_in),
                isLoading = false,
                onClick = {
                    onAction(IntroAction.OnSignInClick)
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            RunFitActionButton(
                text = stringResource(R.string.sign_up),
                isLoading = false,
                onClick = {
                    onAction(IntroAction.OnSignUpClick)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Private composable function that creates a vertical layout for the RunFit logo.
 * @param modifier [Modifier] to be applied to the layout.
 */
@Composable
private fun RunFitLogoVertical(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = LogoIcon,
            contentDescription = "Logo",
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

/**
 * Preview function for [RunFitLogoVertical].
 */
@Preview
@Composable
private fun PreviewRunFitLogoVertical() {
    RunFitTheme {
        RunFitLogoVertical()
    }
}

/**
 * Preview function for [IntroScreen].
 */
@Preview(showBackground = true)
@Composable
private fun PreviewIntroScreen() {
    RunFitTheme {
        IntroScreen(onAction = {})
    }
}