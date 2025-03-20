package com.codermp.auth.presentation.intro

/**
 * Sealed interface representing different actions that can be performed in the introduction screen.
 */
sealed interface IntroAction {
    data object OnSignInClick: IntroAction
    data object OnSignUpClick: IntroAction
}