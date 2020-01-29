package com.example.feature_login.presentation.sign_in.di

import com.example.common.di.FragmentScope
import com.example.feature_login.presentation.sign_in.view.SignInFragment
import dagger.Component

@FragmentScope
@Component(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: SignInFragment)
}