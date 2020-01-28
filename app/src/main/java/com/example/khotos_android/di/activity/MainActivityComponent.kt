package com.example.khotos_android.di.activity

import androidx.fragment.app.FragmentActivity
import com.example.khotos_android.di.ActivityScope
import com.example.khotos_android.view.MainActivity
import dagger.BindsInstance
import dagger.Component

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