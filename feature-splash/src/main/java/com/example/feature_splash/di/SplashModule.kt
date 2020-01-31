package com.example.feature_splash.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.FragmentScope
import com.example.common.di.ViewModelKey
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.intent.TransientStateMapper
import com.example.common.mvi.model.Model
import com.example.common.view.ViewModelFactory
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.interactor.TokenInteractor
import com.example.data.utils.DispatchersProvider
import com.example.feature_splash.intent.SplashIntentFactory
import com.example.feature_splash.model.SplashModel
import com.example.feature_splash.view.SplashViewEvent
import com.example.feature_splash.view.SplashViewModel
import com.example.feature_splash.view.SplashViewModelState
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Provider

@Module
abstract class SplashModule {

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoMap
        @ViewModelKey(SplashViewModel::class)
        @FragmentScope
        fun signInViewModel(
            intentFactory: IntentFactory<SplashViewEvent, SplashViewModelState>,
            model: Model<SplashViewModelState>,
            mapper: StateMapper<SplashViewModelState, SplashViewModelState>
        ): ViewModel {
            return SplashViewModel(intentFactory, model, mapper)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesModel(dispatchersProvider: DispatchersProvider): Model<SplashViewModelState> {
            return SplashModel(dispatchersProvider)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesIntentFactory(apiServiceAdapter: ApiServiceAdapter,
                                  dispatchersProvider: DispatchersProvider,
                                  tokenInteractor: TokenInteractor): IntentFactory<SplashViewEvent, SplashViewModelState> {
            return SplashIntentFactory(apiServiceAdapter, dispatchersProvider, tokenInteractor)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesStateMapper(): StateMapper<SplashViewModelState, SplashViewModelState> {
            return TransientStateMapper()
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesFactory(viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
            return ViewModelFactory(viewModels)
        }


    }
}