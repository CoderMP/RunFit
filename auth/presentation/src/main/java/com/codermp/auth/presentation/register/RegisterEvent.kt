package com.codermp.auth.presentation.register

import com.codermp.core.presentation.ui.UiText

/**
 * Sealed interface representing events related to registration.
 */
sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data class Error(val error: UiText): RegisterEvent
}
