package com.codermp.auth.domain

/**
 * Interface class that exposes validator methods for various patterns. (e.g. email, password)
 */
interface PatternValidator {
    /**
     * Interface function that validates a string against a pattern.
     *
     * @param value The string to validate.
     * @return True if the string matches the pattern, false otherwise.
     */
    fun matches(value: String): Boolean
}