package com.codermp.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.codermp.auth.presentation.R
import com.codermp.core.presentation.designsystem.EmailIcon
import com.codermp.core.presentation.designsystem.Poppins
import com.codermp.core.presentation.designsystem.RunFitTheme
import com.codermp.core.presentation.designsystem.components.GradientBackground
import com.codermp.core.presentation.designsystem.components.RunFitActionButton
import com.codermp.core.presentation.designsystem.components.RunFitPasswordTextField
import com.codermp.core.presentation.designsystem.components.RunFitTextField
import com.codermp.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

/**
 * Composable function that creates the state-less login screen.
 * @param onLoginSuccess The function to call when the login is successful.
 * @param onSignUpClick The function to call when the sign up button is clicked.
 * @param viewModel The [koinViewModel] for the login screen.
 */
@Composable
fun LoginScreenRoot(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is LoginEvent.LoginSuccess -> {
                keyboardController?.hide()

                Toast.makeText(
                    context,
                    context.getString(R.string.login_success),
                    Toast.LENGTH_SHORT
                ).show()

                onLoginSuccess()
            }
            is LoginEvent.Error -> {
                keyboardController?.hide()

                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    LoginScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is LoginAction.OnRegisterClick -> onSignUpClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

/**
 * Composable function that creates the state-full login screen.
 */
@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login_header),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(id = R.string.login_welcome_text),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(48.dp))
            RunFitTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = null,
                keyboardType = KeyboardType.Email,
                title = stringResource(id = R.string.email),
                hint = stringResource(id = R.string.example_email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            RunFitPasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onAction(LoginAction.OnTogglePasswordVisibility)
                },
                title = stringResource(id = R.string.password),
                hint = stringResource(id = R.string.password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            RunFitActionButton(
                text = stringResource(id = R.string.sign_in),
                isLoading = state.isLoggingIn,
                enabled = state.canLogin && !state.isLoggingIn,
                onClick = {
                    onAction(LoginAction.OnLoginClick)
                }
            )
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .imePadding()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = Poppins,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        ) {
                            append(stringResource(id = R.string.no_account) + " ")
                            pushStringAnnotation(
                                tag = "clickable_text",
                                annotation = stringResource(id = R.string.register)
                            )
                            withLink(
                                link = LinkAnnotation.Clickable(
                                    tag = "clickable_text",
                                    linkInteractionListener = {
                                        onAction(LoginAction.OnRegisterClick)
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
                                    append(stringResource(id = R.string.register))
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    RunFitTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}