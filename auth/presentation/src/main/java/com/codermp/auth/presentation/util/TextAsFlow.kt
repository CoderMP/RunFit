package com.codermp.auth.presentation.util

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow

/**
 * Extension function to convert a [TextFieldState] to a [Flow] of [String].
 */
fun TextFieldState.textAsFlow(): Flow<CharSequence> = snapshotFlow { text }