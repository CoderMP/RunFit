package com.codermp.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codermp.core.presentation.designsystem.RunFitBlack
import com.codermp.core.presentation.designsystem.RunFitGray
import com.codermp.core.presentation.designsystem.RunFitTheme

/**
 * Composable function that defines an action button.
 * @param text The text to be displayed on the button.
 * @param isLoading A boolean indicating whether the button is currently loading.
 * @param modifier [Modifier] to be applied to the button.
 * @param enabled A boolean indicating whether the button is enabled.
 * @param onClick The action to be performed when the button is clicked.
 */
@Composable
fun RunFitActionButton(
    text: String,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = RunFitGray,
            disabledContentColor = RunFitBlack
        ),
        shape = RoundedCornerShape(100f),
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = text,
                modifier = Modifier
                    .alpha(if(isLoading) 0f else 1f),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
private fun PreviewRunFitActionButton() {
    val loadingState = remember { mutableStateOf(false) }

    RunFitTheme {
        RunFitActionButton(
            text = "Login",
            isLoading = loadingState.value,
            onClick = { loadingState.value = !loadingState.value}
        )
    }
}