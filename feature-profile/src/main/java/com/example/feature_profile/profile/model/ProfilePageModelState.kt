package com.example.feature_profile.profile.model

import com.example.common.mvi.model.ModelState
import com.example.data.model.user.UserMetadata
import com.example.data.model.user.UserWithPhotos
import java.util.*

data class ProfilePageModelState(val userWithPhotos: UserWithPhotos,
                                   val isLoading: Boolean,
                                   val lastPageLoaded: Int,
                                   val isLastPage: Boolean) : ModelState {
    companion object {
        fun default() = ProfilePageModelState(
            UserWithPhotos(UserMetadata(0L, "", Date(), null), emptyList()),
            true, -1, false
        )
    }
}

