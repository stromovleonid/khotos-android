package com.example.khotos_android.navigation

import androidx.fragment.app.Fragment
import com.example.common.navigation.Navigator
import com.example.feature_login.presentation.sign_in.view.SignInFragment
import com.example.feature_splash.view.SplashFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

@FlowPreview
@ExperimentalCoroutinesApi
class NavigationHandler(private val cicerone: Cicerone<Router>): Navigator {

    override fun signIn() = navigateTo(AppScreen.SignInScreen)

    override fun splash() = navigateTo(AppScreen.SplashScreen)

    override fun exit() = cicerone.router.exit()

    private fun navigateTo(screen: AppScreen) = cicerone.router.navigateTo(screen)
}

@FlowPreview
@ExperimentalCoroutinesApi
private sealed class AppScreen: SupportAppScreen() {

    object SignInScreen: AppScreen() {
        override fun getFragment(): Fragment = SignInFragment.newInstance()
    }

    object SplashScreen: AppScreen() {
        override fun getFragment(): Fragment = SplashFragment.newInstance()
    }
}


