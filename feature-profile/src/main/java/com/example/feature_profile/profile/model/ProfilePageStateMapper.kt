package com.example.feature_profile.profile.model

import com.example.common.mvi.intent.StateMapper
import com.example.feature_photos.feed.view.PhotosFeedCell
import com.example.feature_profile.profile.view.ProfilePageViewState
import com.example.feature_profile.profile.view.UserImageInfo

class ProfilePageStateMapper : StateMapper<ProfilePageViewState, ProfilePageModelState> {
    override fun map(modelState: ProfilePageModelState): ProfilePageViewState {
        val cells = modelState.userWithPhotos.photos.map { PhotosFeedCell.PhotoCell(it.id, it.url) }
            .toMutableList<PhotosFeedCell>()
        if (modelState.isLoading) cells.add(PhotosFeedCell.LoadingCell)
        val user = modelState.userWithPhotos.user
        return ProfilePageViewState(
            user.username,
            UserImageInfo(user.avatarUrl ?: modelState.userWithPhotos.photos.firstOrNull()?.url),
            cells
        )
    }
}