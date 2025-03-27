package com.codermp.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codermp.auth.domain.UserDataValidator
import com.codermp.auth.presentation.R
import com.codermp.core.presentation.designsystem.CheckIcon
import com.codermp.core.presentation.designsystem.CrossIcon
import com.codermp.core.presentation.designsystem.EmailIcon
import com.codermp.core.presentation.designsystem.Poppins
import com.codermp.core.presentation.designsystem.RunFitDarkRed
import com.codermp.core.presentation.designsystem.RunFitGreen
import com.codermp.core.presentation.designsystem.RunFitTheme
import com.codermp.core.presentation.designsystem.components.GradientBackground
import com.codermp.core.presentation.designsystem.components.RunFitActionButton
import com.codermp.core.presentation.designsystem.components.RunFitPasswordTextField
import com.codermp.core.presentation.designsystem.components.RunFitTextField
import com.codermp.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

/**
 * State-ful composable function that displays the registration screen.
 * @param onLoginClick The action to perform when the user clicks on the login text.
 * @param onSuccessfulRegistration The action to perform when the registration is successful.
 * @param viewModel The [RegisterViewModel] to use for the registration screen.
 */
@Composable
fun RegisterScreenRoot(
    onLoginClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(flow = viewModel.events) { event ->
        when(event) {
            is RegisterEvent.RegistrationSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.registration_success,
                    Toast.LENGTH_SHORT
                ).show()
                onSuccessfulRegistration()
            }
            is RegisterEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    RegisterScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is RegisterAction.OnLoginClick -> onLoginClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

/**
 * State-less composable function that displays the registration screen.
 * @param state The [RegisterState] to use for the registration screen.
 * @param onAction The [RegisterAction] to perform when the user interacts with the registration screen.
 */
@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Poppins,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        append(stringResource(id = R.string.already_have_an_account) + " ")
                        pushStringAnnotation(
                            tag = "clickable_text",
                            annotation = stringResource(id = R.string.sign_in)
                        )
                        withLink(
                            link = LinkAnnotation.Clickable(
                                tag = "clickable_text",
                                linkInteractionListener = {
                                    onAction(RegisterAction.OnLoginClick)
                                }
                            )
                        ) {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontFamily = Poppins
                                )
                            ) {
                                append(stringResource(id = R.string.sign_in))
                            }
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
            RunFitTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = if (state.isEmailValid) {
                    CheckIcon
                } else {
                    null
                },
                hint = stringResource(id = R.string.example_email),
                title = stringResource(id = R.string.email),
                additionalInfo = stringResource(id = R.string.must_be_valid_email),
                keyboardType = KeyboardType.Email,
                keyboardAction = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )
            Spacer(modifier = Modifier.height(16.dp))
            RunFitPasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                hint = stringResource(id = R.string.password),
                title = stringResource(id = R.string.password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirement(
                text = stringResource(
                    id = R.string.at_least_x_characters,
                    UserDataValidator.MIN_PASSWORD_LENGTH
                ),
                isValid = state.passwordValidationState.hasMinLength,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(id = R.string.at_least_one_number),
                isValid = state.passwordValidationState.hasNumber,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(id = R.string.contains_lowercase_character),
                isValid = state.passwordValidationState.hasLowerCaseCharacter,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(id = R.string.contains_uppercase_character),
                isValid = state.passwordValidationState.hasUpperCaseCharacter,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            RunFitActionButton(
                text = stringResource(id = R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                onClick = { onAction(RegisterAction.OnRegisterClick) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Composable function that displays a password requirement.
 * @param text The text to display.
 * @param isValid Whether or not the requirement is valid.
 * @param modifier The [Modifier] to apply to the requirement.
 */
@Composable
private fun PasswordRequirement(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isValid) {
                CheckIcon
            } else {
                CrossIcon
            },
            contentDescription = null,
            tint = if (isValid) RunFitGreen else RunFitDarkRed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RunFitTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun PreviewPasswordRequirement() {
    RunFitTheme {
        Column {
            PasswordRequirement(
                text = "Password must be at least 8 characters",
                isValid = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirement(
                text = "Password must contain at least one number",
                isValid = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}