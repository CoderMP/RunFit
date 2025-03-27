package com.codermp.auth.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PatternValidatorTest {
    private lateinit var emailPatternValidator: EmailPatternValidator

    @BeforeEach
    fun setUp() {
        emailPatternValidator = EmailPatternValidator
    }

    @ParameterizedTest
    @CsvSource(
        "adam.smith@mail.com, true",
        "adam@mail.com, true",
        "adam@, false",
        "adam, false",
        "adam.mail.com, false",
        "adam@.com, false",
    )
    fun testValidationEmail(email: String, expected: Boolean) {
        val result = emailPatternValidator.matches(email)

        assertThat(result).isEqualTo(expected)
    }
}