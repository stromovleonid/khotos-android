package com.example.feature_profile.profile.di

import androidx.lifecycle.ViewModel
import com.example.common.di.FragmentScope
import com.example.common.di.ViewModelKey
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.UsersApi
import com.example.data.repositories.AuthRepository
import com.example.data.repositories.PhotosRepository
import com.example.data.repositories.UsersRepository
import com.example.data.utils.DispatchersProvider
import com.example.feature_profile.profile.intent.ProfilePageIntentFactory
import com.example.feature_profile.profile.model.ProfilePageModel
import com.example.feature_profile.profile.model.ProfilePageModelState
import com.example.feature_profile.profile.model.ProfilePageStateMapper
import com.example.feature_profile.profile.view.ProfilePageViewEvent
import com.example.feature_profile.profile.view.ProfilePageViewModel
import com.example.feature_profile.profile.view.ProfilePageViewState
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Module
abstract class ProfilePageModule {

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoMap
        @ViewModelKey(ProfilePageViewModel::class)
        @FragmentScope
        fun signInViewModel(
            intentFactory: IntentFactory<ProfilePageViewEvent, ProfilePageModelState>,
            model: Model<ProfilePageModelState>,
            mapper: StateMapper<ProfilePageViewState, ProfilePageModelState>
        ): ViewModel {
            return ProfilePageViewModel(intentFactory, model, mapper)
        }

        @JvmStatic
        @Provides
        fun providesInitialState(): ProfilePageModelState {
            return ProfilePageModelState.default()
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesModel(
            initialState: ProfilePageModelState,
            authRepository: AuthRepository,
            usersRepository: UsersRepository,
            dispatchersProvider: DispatchersProvider
        ): Model<ProfilePageModelState> {
            return ProfilePageModel(authRepository.getCurrentUserId(),
                initialState, usersRepository, dispatchersProvider)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesIntentFactory(
            authRepository: AuthRepository,
            photosRepository: PhotosRepository
        ): IntentFactory<ProfilePageViewEvent, ProfilePageModelState> {
            return ProfilePageIntentFactory(
                authRepository.getCurrentUserId(),
                photosRepository
            )
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesStateMapper(): StateMapper<ProfilePageViewState, ProfilePageModelState> {
            return ProfilePageStateMapper()
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesPhotosFeedApi(apiServiceAdapter: ApiServiceAdapter): UsersApi =
            apiServiceAdapter
    }
}
