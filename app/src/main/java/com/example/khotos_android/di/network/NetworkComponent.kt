package com.example.khotos_android.di.network

import com.example.data.datasources.api.ApiServiceAdapter
import com.example.khotos_android.di.AppScope
import com.example.khotos_android.di.app.AppComponent
import dagger.Component

@AppScope
@Component(modules = [NetworkModule::class], dependencies = [AppComponent::class])
interface NetworkComponent {
    fun getApiServiceAdapter(): ApiServiceAdapter
}