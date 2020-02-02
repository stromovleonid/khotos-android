package com.example.feature_photos.feed.view

import com.example.common.mvi.view.ViewState

inline class PhotosFeedViewState(val cells: List<PhotosFeedCell>): ViewState

sealed class PhotosFeedCell {
    data class PhotoCell(val id: Long, val url: String): PhotosFeedCell()
    object LoadingCell: PhotosFeedCell()
}