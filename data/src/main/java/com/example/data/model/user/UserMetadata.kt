package com.example.data.model.user

import java.util.*

data class UserMetadata(
    val id: Long,
    val username: String,
    val createdAt: Date,
    val avatarUrl: String?
)