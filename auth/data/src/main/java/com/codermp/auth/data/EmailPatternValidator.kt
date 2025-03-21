package com.codermp.auth.data

import android.util.Patterns
import com.codermp.auth.domain.PatternValidator

/**
 * Object class that serves as the implementation of the PatternValidator interface for email patterns.
 */
object EmailPatternValidator: PatternValidator {
    /**
     * Function that validates a string against the email pattern.
     * @param value The string to validate.
     * @return True if the string matches the email pattern, false otherwise.
     */
    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}