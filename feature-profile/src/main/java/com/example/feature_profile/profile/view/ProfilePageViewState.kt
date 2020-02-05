package com.example.feature_profile.profile.view

import com.example.common.mvi.view.ViewState
import com.example.feature_photos.feed.view.PhotosFeedCell

class ProfilePageViewState(val username: String,
                           val userImageInfo: UserImageInfo,
                           val cells: List<PhotosFeedCell>): ViewState

inline class UserImageInfo(val url: String?)
