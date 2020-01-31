package com.example.feature_splash.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.data.datasources.api.AuthApi
import com.example.data.interactor.TokenInteractor
import com.example.data.utils.DispatchersProvider
import com.example.feature_splash.view.SplashViewEvent
import com.example.feature_splash.view.SplashViewModelState
import kotlinx.coroutines.launch

class SplashIntentFactory(
    private val authApi: AuthApi,
    private val dispatchersProvider: DispatchersProvider,
    private val tokenInteractor: TokenInteractor
) : IntentFactory<SplashViewEvent, SplashViewModelState> {
    override suspend fun toIntent(event: SplashViewEvent): Intent<SplashViewModelState> =
        when (event) {
            SplashViewEvent.CheckToken -> Intent.create({
                SplashViewModelState.Loading
            }) { _, model, scope ->
                scope.launch(dispatchersProvider.io) {
                    model.consume(checkToken())
                }

            }
        }

    private suspend fun checkToken(): Intent<SplashViewModelState> {
        val savedToken = tokenInteractor.getToken()
            ?: return Intent.create { SplashViewModelState.NotLogged }

        return try {
            val refreshResult = authApi.refreshToken(savedToken)
            val token = refreshResult.body()
            if (refreshResult.isSuccessful && token != null) {
                tokenInteractor.saveToken(token.token)
                Intent.create { SplashViewModelState.Logged }
            } else {
                Intent.create { SplashViewModelState.NotLogged }
            }
        } catch (e: Exception) {
            Intent.create { SplashViewModelState.NotLogged }
        }
    }
}