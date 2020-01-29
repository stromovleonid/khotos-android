package com.example.feature_login.presentation.sign_in.model

import com.example.common.mvi.model.BaseModel
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class LoginModel(dispatchersProvider: DispatchersProvider) : BaseModel<LoginModelState>(
    LoginModelState.default(),
    dispatchersProvider
)