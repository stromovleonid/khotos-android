package com.example.common.di.app

import android.content.Context
import com.example.common.di.network.AuthModule
import com.example.common.di.network.NetworkModule
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.AuthApi
import com.example.data.datasources.db.PhotosDao
import com.example.data.interactors.token.TokenInteractor
import com.example.data.repositories.AuthRepository
import com.example.data.utils.DispatchersProvider
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [AppModule::class,
        NetworkModule::class,
        AuthModule::class]
)
interface AppComponent {
    fun getDispatchersProvider(): DispatchersProvider

    fun getGson(): Gson

    fun getApiServiceAdapter(): ApiServiceAdapter

    fun getTokenInteractor(): TokenInteractor

    fun getAuthApi(): AuthApi

    fun getAuthRepository(): AuthRepository

    fun getPhotosDao(): PhotosDao

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun context(appContext: Context): Builder
    }
}