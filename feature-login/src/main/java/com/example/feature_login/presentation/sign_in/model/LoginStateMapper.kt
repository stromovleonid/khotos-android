package com.example.feature_login.presentation.sign_in.model

import com.example.common.mvi.intent.StateMapper
import com.example.feature_login.presentation.sign_in.view.LoginViewState

class LoginStateMapper: StateMapper<LoginViewState, LoginModelState> {
    override fun map(modelState: LoginModelState): LoginViewState {
        if (modelState.isSuccess) return LoginViewState.Success
        if (modelState.error != null) return LoginViewState.Error("error", null, modelState.isInputValid())
        if (modelState.isLoading) return LoginViewState.Loading
        return LoginViewState.UserInput(modelState.isInputValid())
    }

    private fun LoginModelState.isInputValid(): Boolean {
        return this.login.length > 3 && this.password.length > 3
    }
}