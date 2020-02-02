package com.example.feature_photos.feed.model

import com.example.common.mvi.model.ModelState
import com.example.data.model.photo.Photo

data class PhotosFeedModelState(val photos: List<Photo>,
                                val isLoading: Boolean,
                                val lastPageLoaded: Int): ModelState {
    companion object {
        fun default() = PhotosFeedModelState(emptyList(), true, -1)
    }
}