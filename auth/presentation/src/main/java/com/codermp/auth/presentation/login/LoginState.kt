package com.codermp.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

/**
 * Data class that represents the state of the login screen.
 * @param email The state of the email text field.
 * @param password The state of the password text field.
 * @param isPasswordVisible Whether the password text field is visible.
 * @param canLogin Whether the login button can be clicked.
 * @param isLoggingIn Whether the login process is in progress.
 */
data class LoginState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false,
)