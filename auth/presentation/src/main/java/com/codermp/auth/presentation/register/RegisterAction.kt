package com.codermp.auth.presentation.register

/**
 * Represents the actions that can be performed on the register screen.
 */
sealed interface RegisterAction {
    data object OnTogglePasswordVisibilityClick: RegisterAction
    data object OnLoginClick: RegisterAction
    data object OnRegisterClick: RegisterAction
}