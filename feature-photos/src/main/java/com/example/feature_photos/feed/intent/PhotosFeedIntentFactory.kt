package com.example.feature_photos.feed.intent

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.intent.IntentFactory
import com.example.data.datasources.api.PhotosFeedApi
import com.example.data.datasources.db.PhotosDao
import com.example.data.model.photo.Photo
import com.example.data.utils.DispatchersProvider
import com.example.feature_photos.feed.model.PhotosFeedModelState
import com.example.feature_photos.feed.view.PhotosFeedViewEvent
import kotlinx.coroutines.launch

class PhotosFeedIntentFactory(
    private val dispatchersProvider: DispatchersProvider,
    private val dao: PhotosDao,
    private val photosApi: PhotosFeedApi,
    private val pageSize: Int
) :
    IntentFactory<PhotosFeedViewEvent, PhotosFeedModelState> {
    override suspend fun toIntent(event: PhotosFeedViewEvent): Intent<PhotosFeedModelState> =
        when (event) {
            PhotosFeedViewEvent.LoadMore -> Intent
                .create({ it.copy(isLoading = true) }) { state, model, scope ->
                    if (state.isLastPage) return@create
                    scope.launch(dispatchersProvider.io) {
                        val pageIndex = state.lastPageLoaded + 1
                        val newPhotos = photosApi.getPhotosFeed(
                            pageIndex,
                            pageSize
                        ).body()!!.map { Photo.fromResponse(it) }
                        dao.addAll(newPhotos)
                        model.consume(Intent.create {
                            it.copy(
                                isLoading = false,
                                lastPageLoaded = pageIndex,
                                isLastPage = newPhotos.isEmpty() || newPhotos.size != pageSize
                            )
                        })
                    }
                }
        }

}