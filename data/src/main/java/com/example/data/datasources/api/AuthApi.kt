package com.example.data.datasources.api

import com.example.data.model.dto.AuthResponse
import retrofit2.Response

interface AuthApi {
    suspend fun login(login: String, password: String): Response<AuthResponse>
    suspend fun refreshToken(token: String): Response<AuthResponse>
}