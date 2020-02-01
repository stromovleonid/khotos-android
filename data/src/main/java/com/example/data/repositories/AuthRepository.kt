package com.example.data.repositories

import com.example.data.datasources.api.AuthApi
import com.example.data.model.dto.AuthResponse
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.withContext

class AuthRepository(private val authApi: AuthApi,
                     private val dispatchersProvider: DispatchersProvider): BaseApiRepository() {
    suspend fun refreshToken(token: String): AuthResponse {
        return withContext(dispatchersProvider.io) {
            executeApiRequest {
                authApi.refreshToken(token)
            }
        }
    }
}