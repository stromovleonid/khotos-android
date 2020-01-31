package com.example.feature_splash.view

import com.example.common.mvi.model.ModelState
import com.example.common.mvi.view.ViewState

sealed class SplashViewModelState: ViewState, ModelState {
    object None: SplashViewModelState()
    object Loading: SplashViewModelState()
    object Logged: SplashViewModelState()
    object NotLogged: SplashViewModelState()
}