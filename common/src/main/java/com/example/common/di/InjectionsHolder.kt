package com.example.common.di

import com.example.common.di.app.AppComponent
import com.example.common.di.network.NetworkComponent

object InjectionsHolder {
    lateinit var appComponent: AppComponent

    lateinit var networkComponent: NetworkComponent
}