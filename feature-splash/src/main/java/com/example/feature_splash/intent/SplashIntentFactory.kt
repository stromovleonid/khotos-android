package com.example.feature_splash.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.model.Model
import com.example.data.interactors.token.TokenInteractor
import com.example.data.model.CheckTokenResult
import com.example.data.utils.DispatchersProvider
import com.example.feature_splash.view.SplashViewEvent
import com.example.feature_splash.view.SplashViewModelState
import kotlinx.coroutines.launch

class SplashIntentFactory(
    private val tokenInteractor: TokenInteractor,
    private val dispatchersProvider: DispatchersProvider
) : IntentFactory<SplashViewEvent, SplashViewModelState> {
    override suspend fun toIntent(event: SplashViewEvent): Intent<SplashViewModelState> =
        when (event) {
            SplashViewEvent.CheckToken -> Intent.create({
                SplashViewModelState.Loading
            }) { _, model, scope ->
                scope.launch(dispatchersProvider.def) {
                    checkToken(model)
                }
            }
        }

    private suspend fun checkToken(model: Model<SplashViewModelState>) {
        when (tokenInteractor.checkToken()) {
            is CheckTokenResult.NoTokenSaved ->
                model.consume(Intent.create { SplashViewModelState.NotLogged })
            is CheckTokenResult.Prolonged ->
                model.consume(Intent.create { SplashViewModelState.Logged })
            is CheckTokenResult.Error ->
                model.consume(Intent.create { SplashViewModelState.NotLogged })
        }
    }
}