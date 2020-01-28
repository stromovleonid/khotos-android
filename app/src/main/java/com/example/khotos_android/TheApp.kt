package com.example.khotos_android

import android.app.Application
import com.example.khotos_android.di.app.AppComponent
import com.example.khotos_android.di.app.DaggerAppComponent
import com.example.khotos_android.di.network.DaggerNetworkComponent
import com.example.khotos_android.di.network.NetworkComponent
import timber.log.Timber

class TheApp : Application() {

    lateinit var appComponent: AppComponent

    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

        networkComponent = DaggerNetworkComponent.builder().appComponent(appComponent).build()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}