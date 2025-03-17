package com.codermp.core.presentation.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Sealed interface that represents text that can be displayed to the user.
 */
sealed interface UiText {
    /**
     * Data class that defines a dynamic string.
     * @param value The value of the string.
     */
    data class DynamicString(val value: String): UiText

    /**
     * Class that represents a string resource.
     * @param id The id of the string resource.
     * @param args The arguments to be used for the string resource.
     */
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ): UiText

    /**
     * Composable Extension function to convert [UiText] to [String].
     * @return The converted [String].
     */
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = id, *args)
        }
    }

    /**
     * Extension function to convert [UiText] to [String].
     * @param context The [Context] to use for the conversion.
     * @return The converted [String].
     */
    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}