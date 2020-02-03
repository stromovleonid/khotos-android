package com.example.data.datasources.api

import com.example.data.model.dto.UserMetadataResponse
import com.example.data.model.dto.UsersResponse
import retrofit2.Response

interface UsersApi {
    suspend fun getUser(id: Long): Response<UserMetadataResponse>

    suspend fun getUsers(
        query: String,
        ignoreCase: Boolean,
        pageIndex: Int,
        pageSize: Int
    ): Response<UsersResponse>
}