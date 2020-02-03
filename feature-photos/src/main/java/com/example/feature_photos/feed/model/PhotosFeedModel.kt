package com.example.feature_photos.feed.model

import com.example.data.repositories.PhotosRepository
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class PhotosFeedModel(
    initialState: PhotosFeedModelState,
    photosRepository: PhotosRepository,
    dispatchersProvider: DispatchersProvider
) : AbstractPhotosFeedModel(initialState, photosRepository, dispatchersProvider) {
    override fun getPhotosFlow(photosRepository: PhotosRepository) = photosRepository.getPhotosFeed()
}

