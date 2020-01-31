package com.example.feature_splash.view

import com.example.common.mvi.view.ViewEvent

sealed class SplashViewEvent: ViewEvent {
    object CheckToken: SplashViewEvent()
}