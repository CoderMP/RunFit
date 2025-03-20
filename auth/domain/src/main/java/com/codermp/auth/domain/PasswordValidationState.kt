package com.codermp.auth.domain

/**
 * Data class that holds the state of password validation.
 * @param hasMinLength Whether the password has a minimum length
 * @param hasNumber Whether the password has a number
 * @param hasLowerCaseCharacter Whether the password has a lowercase character
 * @param hasUpperCaseCharacter Whether the password has an uppercase character
 * @property isValidPassword Whether the password is valid
 */
data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseCharacter: Boolean = false,
    val hasUpperCaseCharacter: Boolean = false
) {
    val isValidPassword: Boolean
        get() = hasMinLength && hasNumber && hasLowerCaseCharacter && hasUpperCaseCharacter
}