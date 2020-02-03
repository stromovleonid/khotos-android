package com.example.feature_profile.profile.model

import com.example.common.mvi.model.ModelState
import com.example.common.mvi.view.ViewState
import com.example.data.model.user.UserWithPhotos

sealed class ProfilePageModelState: ViewState, ModelState {
    data class Data(
        val userWithPhotos: UserWithPhotos
    ): ProfilePageModelState()

    object Loading: ProfilePageModelState()

    class Error(val e: Throwable): ProfilePageModelState()
}

