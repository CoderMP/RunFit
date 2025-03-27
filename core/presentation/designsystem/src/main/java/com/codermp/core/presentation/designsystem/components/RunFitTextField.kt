package com.codermp.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codermp.core.presentation.designsystem.CheckIcon
import com.codermp.core.presentation.designsystem.EmailIcon
import com.codermp.core.presentation.designsystem.R
import com.codermp.core.presentation.designsystem.RunFitTheme

/**
 * Composable function that creates a custom text field.
 * @param state The state of the text field.
 * @param startIcon The start icon of the text field (Optional).
 * @param endIcon The end icon of the text field (Optional).
 * @param hint The hint text of the text field.
 * @param title The title of the text field (Optional).
 * @param modifier The [Modifier] to apply to the text field.
 * @param error The error message to display if the text field is invalid (Optional).
 * @param keyboardType The [KeyboardType] to use for the text field (default is [KeyboardType.Text]).
 * @param keyboardAction The [KeyboardActions] to use for the text field (default is [KeyboardActions()]).
 * @param additionalInfo Additional information to display below the text field (Optional).
 */
@Composable
fun RunFitTextField(
    state: TextFieldState,
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardAction: KeyboardActions = KeyboardActions(),
    additionalInfo: String? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (error != null) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .testTag(tag = stringResource(R.string.text_field_error_test_tag))
                )
            } else if (additionalInfo != null) {
                Text(
                    text = additionalInfo,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .testTag(tag = stringResource(R.string.text_field_additional_info_test_tag))
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            state = state,
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            decorator = { innerBox ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (startIcon != null) {
                        Icon(
                            imageVector = startIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        if (state.text.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                    alpha = 0.4f
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerBox()
                    }
                    if (endIcon != null) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = endIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .testTag(tag = stringResource(R.string.text_field_end_icon_test_tag))
                        )
                    }
                }
            },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 16.dp))
                .background(
                    color = if (isFocused) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (isFocused) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(12.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                }
                .testTag(tag = stringResource(R.string.basic_text_field_test_tag))
        )
    }
}

@Preview
@Composable
private fun PreviewRunFitTextField() {
    RunFitTheme {
        RunFitTextField(
            state = rememberTextFieldState(),
            startIcon = EmailIcon,
            endIcon = CheckIcon,
            hint = "example@test.com",
            title = "Email",
            additionalInfo = "Must be a valid email",
            modifier = Modifier.fillMaxWidth()
        )
    }
}