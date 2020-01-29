package com.example.feature_login.presentation.sign_in.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.view.LoginViewEvent

class LoginIntentFactory : IntentFactory<LoginViewEvent, LoginModelState> {
    override suspend fun toIntent(event: LoginViewEvent): Intent<LoginModelState> {
        return when (event) {
            is LoginViewEvent.LoginChanged -> Intent.create { it.copy(login = event.newLogin) }
            is LoginViewEvent.PasswordChanged -> Intent.create { it.copy(password = event.newPassword) }
            is LoginViewEvent.SignInPressed -> Intent.create { state ->
                state.copy(isLoading = true)
            }
        }
    }
}