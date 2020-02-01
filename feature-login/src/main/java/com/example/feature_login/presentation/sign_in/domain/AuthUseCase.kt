package com.example.feature_login.presentation.sign_in.domain

import com.example.data.interactors.token.TokenInteractor
import com.example.data.repositories.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository,
                  private val tokenInteractor: TokenInteractor) {

    suspend fun auth(login: String, pass: String) {
        val authResult = authRepository.auth(login, pass)
        tokenInteractor.saveToken(authResult.token)
    }
}