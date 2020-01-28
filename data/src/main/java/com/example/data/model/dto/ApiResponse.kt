package com.example.data.model.dto

import java.util.*

interface ApiResponse

data class AuthResponse(val token: String, val metadata: UserMetadataResponse) : ApiResponse

data class UserMetadataResponse(
    val id: Long,
    val username: String,
    val createdAt: Date,
    val avatarUrl: String?
) : ApiResponse

class PhotosResponse : ArrayList<PhotoResponse>(), ApiResponse

data class PhotoResponse(
    val id: Long,
    val author: UserMetadataResponse,
    val url: String,
    val metadata: PhotoMetadataResponse
) : ApiResponse

data class PhotoMetadataResponse(val createdAt: Date) : ApiResponse

class UsersResponse : ArrayList<UserMetadataResponse>(), ApiResponse