package com.example.feature_profile.profile.view

import com.example.common.mvi.view.ViewState
import com.example.data.model.user.UserMetadata
import com.example.feature_photos.feed.view.PhotosFeedCell

class ProfilePageViewState(val user: UserMetadata, val cells: List<PhotosFeedCell>): ViewState