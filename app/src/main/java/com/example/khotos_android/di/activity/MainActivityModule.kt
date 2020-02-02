package com.example.khotos_android.di.activity

import androidx.fragment.app.FragmentActivity
import com.example.common.di.ActivityScope
import com.example.khotos_android.R
import com.example.khotos_android.navigation.NavigationHandler
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@ExperimentalCoroutinesApi
@FlowPreview
@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun providesNavigator(activity: FragmentActivity, cicerone: Cicerone<Router>): NavigationHandler {
        return NavigationHandler(activity, cicerone, R.id.container, R.id.tabs_container)
    }

    @Provides
    @ActivityScope
    fun providesCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }
}