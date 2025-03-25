package com.codermp.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codermp.auth.domain.AuthRepository
import com.codermp.auth.domain.UserDataValidator
import com.codermp.auth.presentation.util.textAsFlow
import com.codermp.core.domain.utils.DataError
import com.codermp.core.domain.utils.Result
import com.codermp.core.presentation.ui.UiText
import com.codermp.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the registration screen.
 * @param userDataValidator The [UserDataValidator] used for validation.
 * @param authRepository The [AuthRepository] used for authentication.
 */
class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow().onEach { email ->
            val isValidEmail = userDataValidator.isValidEmail(email.toString())

            state = state.copy(
                isEmailValid = isValidEmail,
                canRegister = isValidEmail && state.passwordValidationState.isValidPassword && !state.isRegistering
            )
        }.launchIn(viewModelScope)

        state.password.textAsFlow().onEach { password ->
            val passwordValidationState = userDataValidator.validatePassword(password.toString())

            state = state.copy(
                passwordValidationState = passwordValidationState,
                canRegister = state.isEmailValid && passwordValidationState.isValidPassword && !state.isRegistering
            )
        }.launchIn(viewModelScope)
    }

    /**
     * Handles actions related to the registration screen.
     * @param action The [RegisterAction] to handle.
     */
    fun onAction(action: RegisterAction) {
        when(action) {
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            else -> Unit
        }
    }

    /**
     * Registers the user with the provided email and password.
     */
    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)

            val result = authRepository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString().trim()
            )

            state = state.copy(isRegistering = false)

            when(result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(
                            element = RegisterEvent.Error(
                                    error = UiText.StringResource(
                                        com.codermp.auth.presentation.R.string.error_email_exists
                                )
                            )
                        )
                    } else {
                        eventChannel.send(
                            element = RegisterEvent.Error(error = result.error.asUiText())
                        )
                    }
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}