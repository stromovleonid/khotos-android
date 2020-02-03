package com.example.feature_profile.profile.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.data.model.UpdatePhotosRequestResult
import com.example.data.repositories.PhotosRepository
import com.example.feature_profile.profile.model.ProfilePageModelState
import com.example.feature_profile.profile.view.ProfilePageViewEvent
import kotlinx.coroutines.launch

class ProfilePageIntentFactory(
    private val userId: Long,
    private val photosRepository: PhotosRepository
) : IntentFactory<ProfilePageViewEvent, ProfilePageModelState> {
    override suspend fun toIntent(event: ProfilePageViewEvent): Intent<ProfilePageModelState> {
        return when (event) {
            ProfilePageViewEvent.LoadMore -> Intent
                .create({ it.copy(isLoading = true) }) { state, model, scope ->
                    if (state.isLastPage) return@create
                    scope.launch {
                        when (val result = photosRepository.requestUserPhotosUpdate(
                            userId,
                            state.lastPageLoaded
                        )) {
                            is UpdatePhotosRequestResult.Success -> {
                                model.consume(Intent.create {
                                    it.copy(
                                        isLoading = false,
                                        lastPageLoaded = result.pageIndex,
                                        isLastPage = result.isLastPage
                                    )
                                })
                            }
                        }

                    }
                }
        }
    }
}