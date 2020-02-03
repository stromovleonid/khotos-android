package com.example.feature_photos.feed.model

import com.example.common.mvi.model.BaseModel
import com.example.data.repositories.PhotosRepository
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
@FlowPreview
open class PhotosFeedModel(
    initialState: PhotosFeedModelState,
    private val photosRepository: PhotosRepository,
    dispatchersProvider: DispatchersProvider
) : BaseModel<PhotosFeedModelState>(initialState, dispatchersProvider) {

    override fun submitState(newState: PhotosFeedModelState) = Unit

    override fun observe() = photosRepository.getPhotosFeed().map {
        PhotosFeedModelState(
            it,
            false,
            0,
            false

        )
    }
}