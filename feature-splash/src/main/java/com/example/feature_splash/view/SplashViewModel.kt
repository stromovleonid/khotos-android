package com.example.feature_splash.view

import androidx.lifecycle.viewModelScope
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.common.mvi.view.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel(
    intentFactory: IntentFactory<SplashViewEvent, SplashViewModelState>,
    model: Model<SplashViewModelState>,
    stateMapper: StateMapper<SplashViewModelState, SplashViewModelState>
): BaseViewModel<SplashViewEvent, SplashViewModelState, SplashViewModelState>(
    intentFactory, model, stateMapper
) {
    init {
        viewModelScope.launch {
            model.consume(intentFactory.toIntent(SplashViewEvent.CheckToken))
        }
    }
}
