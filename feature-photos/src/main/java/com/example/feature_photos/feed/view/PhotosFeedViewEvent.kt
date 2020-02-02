package com.example.feature_photos.feed.view

import com.example.common.mvi.view.ViewEvent

sealed class PhotosFeedViewEvent: ViewEvent {
    object LoadMore: PhotosFeedViewEvent()
}