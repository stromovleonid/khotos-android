package com.example.feature_photos.feed.model

import com.example.common.mvi.intent.StateMapper
import com.example.feature_photos.feed.view.PhotosFeedCell
import com.example.feature_photos.feed.view.PhotosFeedViewState

class PhotosStateMapper: StateMapper<PhotosFeedViewState, PhotosFeedModelState> {
    override fun map(modelState: PhotosFeedModelState): PhotosFeedViewState {
        val cells = modelState.photos.map { PhotosFeedCell.PhotoCell(it.id, it.url) }.toMutableList<PhotosFeedCell>()
        if (modelState.isLoading) cells.add(PhotosFeedCell.LoadingCell)
        return PhotosFeedViewState(cells)
    }
}