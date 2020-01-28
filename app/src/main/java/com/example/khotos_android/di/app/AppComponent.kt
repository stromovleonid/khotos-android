package com.example.khotos_android.di.app

import com.example.data.utils.DispatchersProvider
import com.example.khotos_android.di.AppScope
import com.google.gson.Gson
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getDispatchersProvider(): DispatchersProvider

    fun getGson(): Gson
}