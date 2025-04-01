package com.codermp.auth.presentation.login

import com.codermp.core.presentation.ui.UiText

/**
 * Sealed interface that represents the different events that can occur on the login screen.
 */
sealed interface LoginEvent {
    data class Error(val error: UiText): LoginEvent
    data object LoginSuccess: LoginEvent
}