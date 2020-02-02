package com.example.feature_photos.feed.di

import com.example.common.di.FragmentScope
import com.example.common.di.app.AppComponent
import com.example.common.di.app.ViewModelFactoryModule
import com.example.feature_photos.feed.view.PhotosFeedFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@FragmentScope
@Component(modules = [PhotosFeedModule::class, ViewModelFactoryModule::class], dependencies = [AppComponent::class])
@ExperimentalCoroutinesApi
interface PhotosFeedComponent {
    fun inject(activity: PhotosFeedFragment)
}