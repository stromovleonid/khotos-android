package com.example.feature_photos.feed.model

import com.example.common.mvi.model.BaseModel
import com.example.data.model.photo.Photo
import com.example.data.repositories.PhotosRepository
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
@FlowPreview
abstract class AbstractPhotosFeedModel(
    initialState: PhotosFeedModelState,
    private val photosRepository: PhotosRepository,
    dispatchersProvider: DispatchersProvider
) : BaseModel<PhotosFeedModelState>(initialState, dispatchersProvider) {

    abstract fun getPhotosFlow(photosRepository: PhotosRepository): Flow<List<Photo>>

    override fun submitState(newState: PhotosFeedModelState) = Unit

    override fun observe() = getPhotosFlow(photosRepository).map {
        PhotosFeedModelState(
            it,
            false,
            0,
            false
        )
    }
}