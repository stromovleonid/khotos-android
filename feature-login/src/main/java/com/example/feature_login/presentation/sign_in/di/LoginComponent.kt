package com.example.feature_login.presentation.sign_in.di

import com.example.common.di.FragmentScope
import com.example.common.di.app.AppComponent
import com.example.common.di.app.ViewModelFactoryModule
import com.example.feature_login.presentation.sign_in.view.SignInFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@FragmentScope
@Component(modules = [LoginModule::class, ViewModelFactoryModule::class], dependencies = [AppComponent::class])
@ExperimentalCoroutinesApi
interface LoginComponent {
    fun inject(activity: SignInFragment)
}