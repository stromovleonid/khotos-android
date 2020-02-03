package com.example.feature_profile.profile.view

import com.example.common.mvi.view.ViewEvent

sealed class ProfilePageViewEvent: ViewEvent {
    object LoadMore: ProfilePageViewEvent()
}