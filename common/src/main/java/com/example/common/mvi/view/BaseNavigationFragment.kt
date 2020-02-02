package com.example.common.mvi.view

import androidx.fragment.app.Fragment
import com.example.common.navigation.NavigationProvider
import com.example.common.navigation.Navigator

abstract class BaseNavigationFragment : Fragment() {
    protected val navigator: Navigator?
        get() = (activity as? NavigationProvider)?.provideNavigator()
}
