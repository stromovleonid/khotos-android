package com.example.feature_login.presentation.sign_in.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.FragmentScope
import com.example.common.di.ViewModelKey
import com.example.common.mvi.intent.StateMapper
import com.example.common.view.ViewModelFactory
import com.example.data.utils.DispatchersProviderImpl
import com.example.feature_login.presentation.sign_in.intent.LoginIntentFactory
import com.example.feature_login.presentation.sign_in.model.LoginModel
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.view.LoginViewState
import com.example.feature_login.presentation.sign_in.view.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Provider

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
        fun signInViewModel(): ViewModel {
            return SignInViewModel(LoginIntentFactory(),
                LoginModel(DispatchersProviderImpl()),
                object : StateMapper<LoginViewState, LoginModelState> {
                    override fun map(modelState: LoginModelState): LoginViewState {
                        return LoginViewState("err", "err", "err", false)
                    }
                })
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providesFactory(viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
            return ViewModelFactory(viewModels)
        }
    }
}