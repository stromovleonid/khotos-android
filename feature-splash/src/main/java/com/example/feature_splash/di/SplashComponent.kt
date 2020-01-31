package com.example.feature_splash.di

import com.example.common.di.FragmentScope
import com.example.common.di.app.AppComponent
import com.example.common.di.app.ViewModelFactoryModule
import com.example.feature_splash.view.SplashFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@FragmentScope
@Component(modules = [SplashModule::class, ViewModelFactoryModule::class], dependencies = [AppComponent::class])
@ExperimentalCoroutinesApi
interface SplashComponent {
    fun inject(activity: SplashFragment)
}