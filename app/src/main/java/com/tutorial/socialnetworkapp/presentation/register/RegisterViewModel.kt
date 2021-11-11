package com.tutorial.socialnetworkapp.presentation.register

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tutorial.socialnetworkapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.EnteredUsername -> {
                _state.value = _state.value.copy(
                    usernameText = event.value
                )
            }
            is RegisterEvent.EnteredEmail -> {
                _state.value = _state.value.copy(
                    emailText = event.value
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _state.value = _state.value.copy(
                    passwordText = event.value
                )
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !_state.value.isPasswordVisible
                )
            }
            is RegisterEvent.Register -> {
                validateUsername(_state.value.usernameText)
                validateEmail(_state.value.emailText)
                validatePassword(_state.value.passwordText)

                if (
                    _state.value.usernameError == null &&
                    _state.value.emailError == null &&
                    _state.value.passwordError == null
                ) {
                    _state.value = _state.value.copy(
                        isFormValid = true
                    )
                }
            }
        }
    }

    private fun validatePassword(password: String) {
        val trimmedPassword = password.trim()
        if (trimmedPassword.isBlank()){
            _state.value = _state.value.copy(
                passwordError = RegisterState.PasswordError.FieldEmpty
            )
            return
        }
        if (trimmedPassword.length < Constants.MIN_PASSWORD_LENGTH){
            _state.value = _state.value.copy(
                passwordError = RegisterState.PasswordError.InputTooShort
            )
            return
        }
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword){
            _state.value = _state.value.copy(
                passwordError = RegisterState.PasswordError.InvalidPassword
            )
            return
        }
        _state.value = _state.value.copy(passwordError = null)
    }

    private fun validateEmail(email: String) {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank()){
            _state.value = _state.value.copy(
                emailError = RegisterState.EmailError.FieldEmpty
            )
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _state.value = _state.value.copy(
                emailError = RegisterState.EmailError.InvalidEmail
            )
            return
        }
        _state.value = _state.value.copy(emailError = null)
    }

    private fun validateUsername(username: String) {
        val trimmedUsername = username.trim()
        if (trimmedUsername.isBlank()){
            _state.value = _state.value.copy(
                usernameError = RegisterState.UsernameError.FieldEmpty
            )
            return
        }
        if (trimmedUsername.length < Constants.MIN_USERNAME_LENGTH){
            _state.value = _state.value.copy(
                usernameError = RegisterState.UsernameError.InputTooShort
            )
            return
        }
        _state.value = _state.value.copy(usernameError = null)
    }
}