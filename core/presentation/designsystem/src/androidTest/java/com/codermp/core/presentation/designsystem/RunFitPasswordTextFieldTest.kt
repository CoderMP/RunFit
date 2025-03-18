package com.codermp.core.presentation.designsystem

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.codermp.core.presentation.designsystem.components.RunFitPasswordTextField
import org.junit.Rule
import org.junit.Test

class RunFitPasswordTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testRunFitPasswordTextField_togglePasswordVisibility() {
        // GIVEN
        var isVisible by mutableStateOf(false)

        composeRule.setContent {
            RunFitTheme {
                RunFitPasswordTextField(
                    state = rememberTextFieldState(),
                    isPasswordVisible = isVisible,
                    onTogglePasswordVisibility = { isVisible = !isVisible },
                    hint = "Password",
                    title = "Password"
                )
            }
        }

        // WHEN
        composeRule.onNodeWithTag(testTag = "passwordTextField").performTextInput(text = "password1234")
        composeRule.onNodeWithTag(testTag = "passwordVisibilityToggle").performClick()

        // THEN
        composeRule.onNodeWithTag(testTag = "passwordTextField").assertTextEquals("password1234")
        composeRule.onNodeWithTag(testTag = "passwordVisibilityToggle").assertContentDescriptionEquals("Show password")
    }
}