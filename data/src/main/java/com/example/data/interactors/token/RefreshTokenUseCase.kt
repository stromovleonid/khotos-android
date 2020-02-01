package com.example.data.interactors.token

import com.example.data.repositories.AuthRepository

class RefreshTokenUseCase(private val authRepository: AuthRepository) {
    suspend fun refreshToken(token: String): String = authRepository.refreshToken(token).token
}