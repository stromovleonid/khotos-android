package com.example.data.model.user

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.model.photo.Photo

data class UserWithPhotos(
    @Embedded
    val user: UserMetadata,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId"
    )
    val photos: List<Photo>
)