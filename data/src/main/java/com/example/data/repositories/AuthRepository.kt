package com.example.data.repositories

import com.example.data.datasources.api.AuthApi
import com.example.data.model.dto.AuthResponse
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class AuthRepository @Inject constructor(private val authApi: AuthApi,
                                         private val dispatchersProvider: DispatchersProvider): BaseApiRepository() {

    //TODO update outside, on successful auth
    private var currentUserId: Long = 0

    suspend fun refreshToken(token: String): AuthResponse {
        return withContext(dispatchersProvider.io) {
            executeApiRequest {
                authApi.refreshToken(token).apply {
                    currentUserId =  body()?.metadata?.id ?: 0
                }
            }
        }
    }

    suspend fun auth(login: String, pass: String): AuthResponse {
        return withContext(dispatchersProvider.io) {
            executeApiRequest {
                authApi.login(login, pass).apply {
                    currentUserId =  body()?.metadata?.id ?: 0
                }
            }
        }
    }

    fun getCurrentUserId(): Long = currentUserId
}