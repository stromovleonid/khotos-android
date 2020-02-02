package com.example.data.interactors.token

import com.example.data.repositories.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RefreshTokenUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun refreshToken(token: String): String = authRepository.refreshToken(token).token
}