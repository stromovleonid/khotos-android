package com.example.data.model.photo

import com.example.data.model.dto.PhotoMetadataResponse
import java.util.*

data class PhotoMetadata(val createdAt: Date) {
    companion object {
        fun fromResponse(response: PhotoMetadataResponse) = PhotoMetadata(response.createdAt)
    }
}
