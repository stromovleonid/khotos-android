package com.example.common.di.network

import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.AuthApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {
    @Provides
    @Singleton
    fun providesAuthApi(apiServiceAdapter: ApiServiceAdapter): AuthApi =
        apiServiceAdapter

}