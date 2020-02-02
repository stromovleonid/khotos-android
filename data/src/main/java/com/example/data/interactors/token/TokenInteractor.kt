package com.example.data.interactors.token

import com.example.data.exceptions.NetworkConnectionException
import com.example.data.model.CheckTokenResult
import javax.inject.Inject
import javax.inject.Singleton

private const val TOKEN = "token"

@Singleton
class TokenInteractor @Inject constructor(
    private val storage: SecurePersistentKeyValueStorage,
    private val refreshTokenUseCase: RefreshTokenUseCase
) {

    private var cache: String? = null

    suspend fun checkToken(): CheckTokenResult {
        val savedToken = getToken()
            ?: return CheckTokenResult.NoTokenSaved

        return try {
            saveToken(refreshTokenUseCase.refreshToken(savedToken))
            CheckTokenResult.Prolonged
        } catch (e: Exception) {
            CheckTokenResult.Error(NetworkConnectionException())
        }
    }

    fun saveToken(token: String) = synchronized(this) {
        cache = token
        storage.put(TOKEN, token)
    }

    private fun getToken(): String? = synchronized(this) {
        if (cache == null) {
            val token = storage.get<String>(TOKEN)
            cache = token
        }
        return cache
    }
}