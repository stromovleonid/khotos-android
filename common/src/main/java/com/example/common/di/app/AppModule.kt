package com.example.common.di.app

import com.example.common.di.DateTimeFormatQualifier
import com.example.data.utils.DispatchersProvider
import com.example.data.utils.DispatchersProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
abstract class AppModule {

    @Binds
    abstract fun bindsDispatchersProvider(provider: DispatchersProviderImpl): DispatchersProvider

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Reusable
        fun provideGson(@DateTimeFormatQualifier dateTimeFormat: String): Gson
                = GsonBuilder().setDateFormat(dateTimeFormat).create()

        @JvmStatic
        @Provides
        @Reusable
        @DateTimeFormatQualifier
        fun provideDateTimeFormat(): String = "dd-MM-yyyy, HH:mm"
    }

}