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
                .create({ it.copy(isLoading = true) }) { state, _, scope ->
                    scope.launch(dispatchersProvider.io) {
                        val newPhotos = photosApi.getPhotosFeed(state.lastPageLoaded + 1, pageSize)
                        dao.addAll(newPhotos.body()!!.map { Photo.fromResponse(it) })
                    }
                }
        }

}