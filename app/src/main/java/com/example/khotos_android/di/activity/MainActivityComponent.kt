package com.example.khotos_android.di.activity

import androidx.fragment.app.FragmentActivity
import com.example.common.di.ActivityScope
import com.example.khotos_android.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@ActivityScope
@Component(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): MainActivityComponent

        @BindsInstance
        fun activity(activity: FragmentActivity): Builder
    }
}