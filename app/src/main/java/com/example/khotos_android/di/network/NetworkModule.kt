package com.example.khotos_android.di.network

import com.example.data.datasources.api.ApiService
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.khotos_android.BuildConfig
import com.example.khotos_android.di.AppScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @AppScope
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @AppScope
    fun provideRetrofit(httpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    @AppScope
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @AppScope
    fun provideApiServiceAdapter(apiService: ApiService): ApiServiceAdapter =
        ApiServiceAdapter(apiService)
}