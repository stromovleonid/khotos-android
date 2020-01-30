package com.example.common.di.app

import com.example.common.di.network.NetworkModule
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.utils.DispatchersProvider
import com.google.gson.Gson
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun getDispatchersProvider(): DispatchersProvider

    fun getGson(): Gson

    fun getApiServiceAdapter(): ApiServiceAdapter
}