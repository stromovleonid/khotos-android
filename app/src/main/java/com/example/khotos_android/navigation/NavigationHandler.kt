package com.example.khotos_android.navigation

import androidx.fragment.app.Fragment
import com.example.feature_login.presentation.sign_in.view.SignInFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

@FlowPreview
@ExperimentalCoroutinesApi
class NavigationHandler(private val cicerone: Cicerone<Router>) {

    fun exit() = cicerone.router.exit()

    fun navigateTo(screen: AppScreen) = cicerone.router.navigateTo(screen)
}

@FlowPreview
@ExperimentalCoroutinesApi
sealed class AppScreen: SupportAppScreen() {

    object SignInScreen: AppScreen() {
        override fun getFragment(): Fragment = SignInFragment.newInstance()
    }
}


