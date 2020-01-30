package com.example.common.di.network

import com.example.data.datasources.api.ApiServiceAdapter
import com.example.common.di.AppScope
import com.example.common.di.app.AppComponent
import dagger.Component

@AppScope
@Component(modules = [NetworkModule::class], dependencies = [AppComponent::class])
interface NetworkComponent {
    fun getApiServiceAdapter(): ApiServiceAdapter
}