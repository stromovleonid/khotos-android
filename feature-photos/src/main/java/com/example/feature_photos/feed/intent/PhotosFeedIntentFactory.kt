package com.example.feature_photos.feed.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.data.model.UpdatePhotosRequestResult
import com.example.data.repositories.PhotosRepository
import com.example.feature_photos.feed.model.PhotosFeedModelState
import com.example.feature_photos.feed.view.PhotosFeedViewEvent
import kotlinx.coroutines.launch

class PhotosFeedIntentFactory(
    private val photosRepository: PhotosRepository
) :
    IntentFactory<PhotosFeedViewEvent, PhotosFeedModelState> {
    override suspend fun toIntent(event: PhotosFeedViewEvent): Intent<PhotosFeedModelState> =
        when (event) {
            PhotosFeedViewEvent.LoadMore -> Intent
                .create({ it.copy(isLoading = true) }) { state, model, scope ->
                    if (state.isLastPage) return@create
                    scope.launch {
                        when (val result = photosRepository.requestPhotosFeedPage(state.lastPageLoaded)) {
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