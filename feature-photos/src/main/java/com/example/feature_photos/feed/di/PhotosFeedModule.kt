package com.example.feature_photos.feed.di

import androidx.lifecycle.ViewModel
import com.example.common.di.FragmentScope
import com.example.common.di.ViewModelKey
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.data.repositories.PhotosRepository
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
            photosRepository: PhotosRepository,
            dispatchersProvider: DispatchersProvider
        ): Model<PhotosFeedModelState> {
            return PhotosFeedModel(initialState, photosRepository, dispatchersProvider)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesIntentFactory(
            photosRepository: PhotosRepository
        ): IntentFactory<PhotosFeedViewEvent, PhotosFeedModelState> {
            return PhotosFeedIntentFactory(
                photosRepository
            )
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesStateMapper(): StateMapper<PhotosFeedViewState, PhotosFeedModelState> {
            return PhotosStateMapper()
        }
    }
}
