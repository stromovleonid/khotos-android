package com.example.feature_login.presentation.sign_in.model

import com.example.common.mvi.model.ModelState

data class LoginModelState(val login: String,
                           val password: String,
                           val isLoading: Boolean,
                           val error: Throwable?,
                           val isSuccess: Boolean): ModelState {
    companion object {
        fun default() = LoginModelState("", "", false, null, false)
    }
}