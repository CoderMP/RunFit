package com.codermp.auth.domain

/**
 * Class that holds validators for different types of user data.
 */
class UserDataValidator(
    private val patternValidator: PatternValidator
) {
    /**
     * Function that validates an email address.
     * @param email The email address to validate.
     * @return True if the email is valid, false otherwise.
     */
    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email.trim())
    }

    /**
     * Function that validates a password.
     *
     * @param password The password to validate.
     * @return A [PasswordValidationState] object containing the validation results.
     */
    fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }

        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasDigit,
            hasUpperCaseCharacter = hasUpperCase,
            hasLowerCaseCharacter = hasLowerCase
        )
    }

    /**
     * Companion object containing constants related to user data validation.
     * @property MIN_PASSWORD_LENGTH The minimum length for a password.
     */
    companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}