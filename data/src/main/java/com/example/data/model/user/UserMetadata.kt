package com.example.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.datasources.db.Converters
import com.example.data.model.dto.UserMetadataResponse
import java.util.*

@Entity
@TypeConverters(Converters::class)
data class UserMetadata(
    @PrimaryKey
    val id: Long,
    val username: String,
    val createdAt: Date,
    val avatarUrl: String?
) {
    companion object {
        fun fromResponse(response: UserMetadataResponse) = UserMetadata(
            response.id,
            response.username,
            response.createdAt,
            response.avatarUrl
        )
    }
}