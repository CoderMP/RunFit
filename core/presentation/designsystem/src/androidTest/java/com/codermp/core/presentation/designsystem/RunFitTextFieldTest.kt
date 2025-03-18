package com.codermp.core.presentation.designsystem

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import com.codermp.core.presentation.designsystem.components.RunFitTextField
import org.junit.Rule
import org.junit.Test

/**
 * Test class for the [RunFitTextField] composable.
 */
class RunFitTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    /**
     * Test case for when a valid email is entered into the text field.
     */
    @Test
    fun testRunFitTextField_enterValidEmail_noError() {
        // GIVEN
        composeRule.setContent {
            RunFitTheme {
                RunFitTextField(
                    state = rememberTextFieldState(),
                    startIcon = EmailIcon,
                    endIcon = CheckIcon,
                    hint = "example@test.com",
                    title = "Email",
                    additionalInfo = "Must be a valid email",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier
                )
            }
        }

        // WHEN
        composeRule.onNodeWithTag(testTag = "inputTextField").performTextInput(text = "adam.smith@test.com")

        // THEN
        composeRule.onNodeWithTag(testTag = "errorText").assertIsNotDisplayed()
        composeRule.onNodeWithTag(testTag = "inputTextField").assertTextEquals("adam.smith@test.com")
    }

    /**
     * Test case for when an invalid email is entered into the text field.
     */
    @Test
    fun testRunFitTextField_enterInvalidEmail_seeError() {
        // GIVEN
        composeRule.setContent {
            RunFitTheme {
                RunFitTextField(
                    state = rememberTextFieldState(),
                    startIcon = EmailIcon,
                    endIcon = CheckIcon,
                    hint = "example@test.com",
                    title = "Email",
                    error = "Must be a valid email",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier
                )
            }
        }

        // WHEN
        composeRule.onNodeWithTag(testTag = "inputTextField").performTextInput(text = "adam.smithtest.com")

        // THEN
        composeRule.onNodeWithTag(testTag = "errorText").assertIsDisplayed()
        composeRule.onNodeWithTag(testTag = "errorText").assertTextEquals("Must be a valid email")
    }
}