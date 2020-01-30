package com.example.feature_login.presentation.sign_in.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.data.datasources.api.AuthApi
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.view.LoginViewEvent
import timber.log.Timber
import java.lang.Exception
import java.lang.RuntimeException

class LoginIntentFactory(private val authApi: AuthApi) : IntentFactory<LoginViewEvent, LoginModelState> {
    override suspend fun toIntent(event: LoginViewEvent): Intent<LoginModelState> {
        return when (event) {
            is LoginViewEvent.LoginChanged -> Intent.create { it.copy(login = event.newLogin) }
            is LoginViewEvent.PasswordChanged -> Intent.create { it.copy(password = event.newPassword) }
            is LoginViewEvent.SignInPressed -> Intent.create({ it.copy(isLoading = true) }) { state ->
                val authResult = try {
                    authApi.login(state.login, state.password)
                } catch (e: Exception) {
                    return@create Intent.create { it.copy(error = RuntimeException()) }
                }

                if (authResult.isSuccessful)
                    Intent.create { it.copy(isSuccess = true) }
                else Intent.create { it.copy(error = RuntimeException()) }
            }
        }
    }
}