package com.example.khotos_android

import android.app.Application
import com.example.common.di.InjectionsHolder
import com.example.common.di.app.DaggerAppComponent
import com.example.common.di.network.DaggerNetworkComponent
import timber.log.Timber

class TheApp : Application() {

    override fun onCreate() {
        super.onCreate()

        InjectionsHolder.appComponent = DaggerAppComponent.create()

        InjectionsHolder.networkComponent =
            DaggerNetworkComponent.builder().appComponent(InjectionsHolder.appComponent).build()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}