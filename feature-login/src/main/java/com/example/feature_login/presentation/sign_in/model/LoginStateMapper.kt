package com.example.feature_login.presentation.sign_in.model

import com.example.common.di.FragmentScope
import com.example.common.mvi.intent.StateMapper
import com.example.feature_login.presentation.sign_in.view.LoginViewState
import javax.inject.Inject

@FragmentScope
class LoginStateMapper @Inject constructor(): StateMapper<LoginViewState, LoginModelState> {
    override fun map(modelState: LoginModelState): LoginViewState {
        if (modelState.isLoading) return LoginViewState.Loading
        if (modelState.error != null) return LoginViewState.Error("error", null, modelState.isInputValid())
        if (modelState.isSuccess) return LoginViewState.Success
        return LoginViewState.UserInput(modelState.isInputValid())
    }

    private fun LoginModelState.isInputValid(): Boolean {
        return this.login.length > 3 && this.password.length > 3
    }
}