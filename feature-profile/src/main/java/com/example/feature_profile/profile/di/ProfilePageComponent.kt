package com.example.feature_profile.profile.di

import com.example.common.di.FragmentScope
import com.example.common.di.app.AppComponent
import com.example.common.di.app.ViewModelFactoryModule
import com.example.feature_profile.profile.view.ProfilePageFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@FragmentScope
@Component(modules = [ProfilePageModule::class, ViewModelFactoryModule::class], dependencies = [AppComponent::class])
@ExperimentalCoroutinesApi
interface ProfilePageComponent {
    fun inject(activity: ProfilePageFragment)
}