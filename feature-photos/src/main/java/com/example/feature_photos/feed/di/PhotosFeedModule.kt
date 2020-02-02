package com.example.feature_photos.feed.di

import androidx.lifecycle.ViewModel
import com.example.common.Constants
import com.example.common.di.FragmentScope
import com.example.common.di.ViewModelKey
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.PhotosFeedApi
import com.example.data.datasources.db.PhotosDao
import com.example.data.utils.DispatchersProvider
import com.example.feature_photos.feed.intent.PhotosFeedIntentFactory
import com.example.feature_photos.feed.model.PhotosFeedModel
import com.example.feature_photos.feed.model.PhotosFeedModelState
import com.example.feature_photos.feed.model.PhotosStateMapper
import com.example.feature_photos.feed.view.PhotosFeedViewEvent
import com.example.feature_photos.feed.view.PhotosFeedViewModel
import com.example.feature_photos.feed.view.PhotosFeedViewState
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@Module
abstract class PhotosFeedModule {

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoMap
        @ViewModelKey(PhotosFeedViewModel::class)
        @FragmentScope
        fun signInViewModel(
            intentFactory: IntentFactory<PhotosFeedViewEvent, PhotosFeedModelState>,
            model: Model<PhotosFeedModelState>,
            mapper: StateMapper<PhotosFeedViewState, PhotosFeedModelState>
        ): ViewModel {
            return PhotosFeedViewModel(intentFactory, model, mapper)
        }

        @JvmStatic
        @Provides
        fun providesInitialState(): PhotosFeedModelState {
            return PhotosFeedModelState.default()
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesModel(
            initialState: PhotosFeedModelState,
            photosDao: PhotosDao,
            dispatchersProvider: DispatchersProvider
        ): Model<PhotosFeedModelState> {
            return PhotosFeedModel(initialState, photosDao, dispatchersProvider, Constants.PHOTOS_PAGE_SIZE)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesIntentFactory(
            dispatchersProvider: DispatchersProvider,
            photosDao: PhotosDao,
            photosFeedApi: PhotosFeedApi
        ): IntentFactory<PhotosFeedViewEvent, PhotosFeedModelState> {
            return PhotosFeedIntentFactory(dispatchersProvider, photosDao, photosFeedApi, Constants.PHOTOS_PAGE_SIZE)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesStateMapper(): StateMapper<PhotosFeedViewState, PhotosFeedModelState> {
            return PhotosStateMapper()
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesPhotosFeedApi(apiServiceAdapter: ApiServiceAdapter): PhotosFeedApi =
            apiServiceAdapter
    }
}
