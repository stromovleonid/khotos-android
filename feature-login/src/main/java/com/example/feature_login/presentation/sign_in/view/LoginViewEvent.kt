package com.example.feature_login.presentation.sign_in.view

import com.example.common.mvi.view.ViewEvent

sealed class LoginViewEvent: ViewEvent {
    data class LoginChanged(val newLogin: String): LoginViewEvent()
    data class PasswordChanged(val newPassword: String): LoginViewEvent()
    object SignInPressed: LoginViewEvent()
}