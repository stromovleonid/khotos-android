package com.example.feature_login.presentation.sign_in.view

import com.example.common.mvi.view.ViewState

class LoginViewState(val loginError: String?,
                     val passwordError: String?,
                     val authError: String?,
                     val isLoading: Boolean): ViewState {
    companion object {
        fun default() = LoginViewState(null, null, null, false)
    }
}