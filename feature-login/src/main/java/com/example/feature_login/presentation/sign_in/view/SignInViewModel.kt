package com.example.feature_login.presentation.sign_in.view

import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.common.mvi.view.BaseViewModel
import com.example.feature_login.presentation.sign_in.model.LoginModelState

class SignInViewModel(
    intentFactory: IntentFactory<LoginViewEvent, LoginModelState>,
    model: Model<LoginModelState>,
    stateMapper: StateMapper<LoginViewState, LoginModelState>
) : BaseViewModel<LoginViewEvent, LoginViewState, LoginModelState>(
    intentFactory, model, stateMapper
)
