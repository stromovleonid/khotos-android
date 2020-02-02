package com.example.common.navigation

interface NavigationProvider {
    fun provideNavigator(): Navigator
}

interface Navigator {
    fun exit()
    fun signIn()
    fun splash()
    fun main()

    fun photosTab()
    fun profileTab()
}