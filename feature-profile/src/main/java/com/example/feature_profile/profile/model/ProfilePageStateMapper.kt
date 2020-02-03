package com.example.feature_profile.profile.model

import com.example.common.mvi.intent.StateMapper
import com.example.feature_photos.feed.view.PhotosFeedCell
import com.example.feature_profile.profile.view.ProfilePageViewState

class ProfilePageStateMapper: StateMapper<ProfilePageViewState, ProfilePageModelState> {
    override fun map(modelState: ProfilePageModelState): ProfilePageViewState {
        val cells = modelState.userWithPhotos.photos.map { PhotosFeedCell.PhotoCell(it.id, it.url) }.toMutableList<PhotosFeedCell>()
        if (modelState.isLoading) cells.add(PhotosFeedCell.LoadingCell)
        return ProfilePageViewState(modelState.userWithPhotos.user, cells)
    }
}