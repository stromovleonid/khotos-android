package com.example.data.model.photo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.datasources.db.Converters
import com.example.data.model.dto.PhotoResponse

@Entity
@TypeConverters(Converters::class)
data class Photo(
    @PrimaryKey
    val id: Long,
    val url: String,
    @Embedded
    val metadata: PhotoMetadata,
    val authorId: Long
) {
    companion object {
        fun fromResponse(photoResponse: PhotoResponse) = Photo(
            photoResponse.id,
            photoResponse.url,
            PhotoMetadata.fromResponse(photoResponse.metadata),
            photoResponse.author.id
        )
    }
}
