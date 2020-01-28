package com.example.khotos_android.navigation

import com.example.feature_login.presentation.sign_in.SignInFragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NavigationHandler(private val cicerone: Cicerone<Router>) {

    fun exit() = cicerone.router.exit()

    fun navigateTo(screen: AppScreen) = cicerone.router.navigateTo(screen)
}

sealed class AppScreen: SupportAppScreen() {

    object SignInScreen: AppScreen() {
        override fun getFragment() = SignInFragment.newInstance()
    }
}


