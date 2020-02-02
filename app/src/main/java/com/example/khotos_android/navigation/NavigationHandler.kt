package com.example.khotos_android.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.common.navigation.Navigator
import com.example.feature_login.presentation.sign_in.view.SignInFragment
import com.example.feature_main_screen.view.TabsFragment
import com.example.feature_splash.view.SplashFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class NavigationHandler(
    private val activity: FragmentActivity,
    private val cicerone: Cicerone<Router>,
    @IdRes private val mainContainerId: Int,
    @IdRes private val tabsContainerId: Int
) : Navigator {

    private val photosTabNavigator: ru.terrakok.cicerone.Navigator by lazy {
        Timber.i("photos tab navigator created")
        SupportAppNavigator(activity, tabsContainerId).apply {
            applyCommands(arrayOf(Forward(AppScreen.SignInScreen)))
        }
    }

    private val profileTabNavigator: ru.terrakok.cicerone.Navigator by lazy {
        Timber.i("profile tab navigator created")
        SupportAppNavigator(activity, tabsContainerId).apply {
            applyCommands(arrayOf(Forward(AppScreen.SplashScreen)))
        }
    }

    private val mainNavigator = SupportAppNavigator(activity, mainContainerId)

    init {
        cicerone.navigatorHolder.setNavigator(mainNavigator)
    }

    private fun navigateTo(screen: AppScreen) = cicerone.router.navigateTo(screen)

    override fun exit() = cicerone.router.exit()

    override fun signIn() = navigateTo(AppScreen.SignInScreen)

    override fun splash() = navigateTo(AppScreen.SplashScreen)

    override fun main() = navigateTo(AppScreen.MainScreen)

    override fun photosTab() {
        Timber.i("photos tab navigator applied")
        cicerone.navigatorHolder.setNavigator(photosTabNavigator)
    }

    override fun profileTab() {
        Timber.i("profile tab navigator applied")
        cicerone.navigatorHolder.setNavigator(profileTabNavigator)
    }
}

@FlowPreview
@ExperimentalCoroutinesApi
private sealed class AppScreen : SupportAppScreen() {

    object SignInScreen : AppScreen() {
        override fun getFragment(): Fragment = SignInFragment.newInstance()
    }

    object SplashScreen : AppScreen() {
        override fun getFragment(): Fragment = SplashFragment.newInstance()
    }

    object MainScreen : AppScreen() {
        override fun getFragment(): Fragment = TabsFragment.newInstance()
    }
}


