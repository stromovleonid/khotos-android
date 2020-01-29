package com.example.khotos_android.di.activity

import androidx.fragment.app.FragmentActivity
import com.example.khotos_android.R
import com.example.common.di.ActivityScope
import com.example.khotos_android.navigation.NavigationHandler
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideNavigator(activity: FragmentActivity): NavigationHandler {
        val cicerone = Cicerone.create()
        cicerone.navigatorHolder.setNavigator(SupportAppNavigator(activity, R.id.container))
        return NavigationHandler(cicerone)
    }
}