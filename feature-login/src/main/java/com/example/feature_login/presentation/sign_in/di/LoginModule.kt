package com.example.feature_login.presentation.sign_in.di

import androidx.lifecycle.ViewModel
import com.example.common.di.FragmentScope
import com.example.common.di.ViewModelKey
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.interactor.TokenInteractor
import com.example.data.utils.DispatchersProvider
import com.example.feature_login.presentation.sign_in.intent.LoginIntentFactory
import com.example.feature_login.presentation.sign_in.model.LoginModel
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.model.LoginStateMapper
import com.example.feature_login.presentation.sign_in.view.LoginViewEvent
import com.example.feature_login.presentation.sign_in.view.LoginViewState
import com.example.feature_login.presentation.sign_in.view.SignInViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Module
abstract class LoginModule {

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoMap
        @ViewModelKey(SignInViewModel::class)
        @FragmentScope
        fun signInViewModel(
            intentFactory: IntentFactory<LoginViewEvent, LoginModelState>,
            model: Model<LoginModelState>,
            mapper: StateMapper<LoginViewState, LoginModelState>
        ): ViewModel {
            return SignInViewModel(intentFactory, model, mapper)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesModel(dispatchersProvider: DispatchersProvider): Model<LoginModelState> {
            return LoginModel(dispatchersProvider)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesIntentFactory(apiServiceAdapter: ApiServiceAdapter, dispatchersProvider: DispatchersProvider, tokenInteractor: TokenInteractor): IntentFactory<LoginViewEvent, LoginModelState> {
            return LoginIntentFactory(apiServiceAdapter, dispatchersProvider, tokenInteractor)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesStateMapper(): StateMapper<LoginViewState, LoginModelState> {
            return LoginStateMapper()
        }
    }
}