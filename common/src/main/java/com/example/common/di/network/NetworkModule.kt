package com.example.common.di.network

import com.example.common.BuildConfig
import com.example.data.datasources.api.ApiService
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.common.di.AppScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiServiceAdapter(apiService: ApiService): ApiServiceAdapter =
        ApiServiceAdapter(apiService)
}