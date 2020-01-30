package com.example.feature_login.presentation.sign_in.view

import com.example.common.mvi.view.ViewState

sealed class LoginViewState: ViewState {
    class UserInput(val isValid: Boolean): LoginViewState()
    object Loading: LoginViewState()
    class Error(val loginError: String?,
                val passwordError: String?,
                val isValid: Boolean): LoginViewState()
    object Success: LoginViewState()
}