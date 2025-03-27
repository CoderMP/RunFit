package com.codermp.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import com.codermp.auth.domain.PasswordValidationState

/**
 * Data class representing the state of the registration screen.
 * @property email The state of the email text field.
 * @property isEmailValid Whether the email is valid.
 * @property password The state of the password text field.
 * @property isPasswordVisible Whether the password is visible.
 * @property passwordValidationState The validation state of the password.
 * @property isRegistering Whether the registration is in progress.
 * @property canRegister Whether the registration is enabled.
 */
data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)