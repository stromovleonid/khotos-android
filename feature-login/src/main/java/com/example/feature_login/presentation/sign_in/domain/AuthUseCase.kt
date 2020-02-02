package com.example.feature_login.presentation.sign_in.domain

import com.example.common.di.FragmentScope
import com.example.data.interactors.token.TokenInteractor
import com.example.data.repositories.AuthRepository
import javax.inject.Inject

@FragmentScope
class AuthUseCase @Inject constructor(private val authRepository: AuthRepository,
                  private val tokenInteractor: TokenInteractor) {

    suspend fun auth(login: String, pass: String) {
        val authResult = authRepository.auth(login, pass)
        tokenInteractor.saveToken(authResult.token)
    }
}