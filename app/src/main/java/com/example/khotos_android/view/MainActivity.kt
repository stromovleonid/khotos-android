package com.example.khotos_android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.common.navigation.NavigationProvider
import com.example.common.navigation.Navigator
import com.example.khotos_android.R
import com.example.khotos_android.di.activity.DaggerMainActivityComponent
import com.example.khotos_android.navigation.NavigationHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity(), NavigationProvider {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.builder()
            .activity(this)
            .build()
            .inject(this)

        navigationHandler.splash()
    }

    override fun provideNavigator(): Navigator {
        return navigationHandler
    }
}
