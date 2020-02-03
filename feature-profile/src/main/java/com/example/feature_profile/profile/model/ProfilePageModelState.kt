package com.example.feature_profile.profile.model

import com.example.data.model.user.UserMetadata
import com.example.feature_photos.feed.model.PhotosFeedModelState

data class ProfilePageModelState(
    val profile: UserMetadata,
    val userPhotosState: PhotosFeedModelState
)