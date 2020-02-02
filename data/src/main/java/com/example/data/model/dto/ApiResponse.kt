package com.example.data.model.dto

import com.google.gson.annotations.SerializedName
import java.util.*

interface ApiResponse

data class AuthResponse(
    @SerializedName("token") val token: String,
    @SerializedName("metadata") val metadata: UserMetadataResponse) : ApiResponse

data class UserMetadataResponse(
    @SerializedName("id")  val id: Long,
    @SerializedName("username")  val username: String,
    @SerializedName("createdAt")  val createdAt: Date,
    @SerializedName("avatarUrl")  val avatarUrl: String?
) : ApiResponse

class PhotosResponse : ArrayList<PhotoResponse>(), ApiResponse

data class PhotoResponse(
    @SerializedName("id")  val id: Long,
    @SerializedName("author")  val author: UserMetadataResponse,
    @SerializedName("url")  val url: String,
    @SerializedName("metadata")  val metadata: PhotoMetadataResponse
) : ApiResponse

data class PhotoMetadataResponse(@SerializedName("createdAt")  val createdAt: Date) : ApiResponse

class UsersResponse : ArrayList<UserMetadataResponse>(), ApiResponse