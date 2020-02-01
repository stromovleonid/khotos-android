package com.example.feature_login.presentation.sign_in.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.model.Model
import com.example.data.utils.DispatchersProvider
import com.example.feature_login.presentation.sign_in.domain.AuthUseCase
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.view.LoginViewEvent
import kotlinx.coroutines.launch

class LoginIntentFactory(
    private val authUseCase: AuthUseCase,
    private val dispatchersProvider: DispatchersProvider
) : IntentFactory<LoginViewEvent, LoginModelState> {
    override suspend fun toIntent(event: LoginViewEvent): Intent<LoginModelState> {
        return when (event) {
            is LoginViewEvent.LoginChanged -> Intent.create {
                it.copy(
                    login = event.newLogin,
                    error = null,
                    isSuccess = false
                )
            }
            is LoginViewEvent.PasswordChanged -> Intent.create {
                it.copy(
                    password = event.newPassword,
                    error = null,
                    isSuccess = false
                )
            }
            is LoginViewEvent.SignInPressed -> Intent.create({
                it.copy(
                    isLoading = true,
                    error = null,
                    isSuccess = false
                )
            }) { state, model, scope ->
                scope.launch(dispatchersProvider.def) {
                    auth(state, model)
                }
            }
        }
    }

    private suspend fun auth(
        state: LoginModelState,
        model: Model<LoginModelState>
    ) {
        try {
            authUseCase.auth(state.login, state.password)
            model.consume(Intent.create { it.copy(isSuccess = true, isLoading = false, error = null) })
        } catch (e: Exception) {
            model.consume(Intent.create { it.copy(error = e, isLoading = false, isSuccess = false) })
        }
    }
}