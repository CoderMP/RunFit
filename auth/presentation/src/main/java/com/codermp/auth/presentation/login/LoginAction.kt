package com.codermp.auth.presentation.login

/**
 * Sealed interface that represents the different actions that can occur on the login screen.
 */
interface LoginAction {
    data object OnTogglePasswordVisibility: LoginAction
    data object OnLoginClick: LoginAction
    data object OnRegisterClick: LoginAction
}