package com.example.khotos_android

import android.app.Application
import com.example.common.di.InjectionsHolder
import com.example.common.di.app.DaggerAppComponent
import timber.log.Timber

class TheApp : Application() {

    override fun onCreate() {
        super.onCreate()

        InjectionsHolder.appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}