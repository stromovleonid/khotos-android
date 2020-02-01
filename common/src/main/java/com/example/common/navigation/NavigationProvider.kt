package com.example.common.navigation

interface NavigationProvider {
    fun provideNavigator(): Navigator
}

interface Navigator {
    fun signIn()
    fun splash()
    fun exit()
}