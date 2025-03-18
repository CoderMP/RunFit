package com.codermp.core.presentation.designsystem.utils

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class that defines a top toolbar drop down item.
 * @param icon The icon to be displayed.
 * @param title The title to be displayed.
 */
data class DropDownItem(
    val icon: ImageVector,
    val title: String
)