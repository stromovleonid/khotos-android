package com.example.common.di.app

import android.content.Context
import androidx.room.Room
import com.example.common.di.DateTimeFormatQualifier
import com.example.data.datasources.db.AppDatabase
import com.example.data.utils.DispatchersProvider
import com.example.data.utils.DispatchersProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun bindsDispatchersProvider(provider: DispatchersProviderImpl): DispatchersProvider

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Reusable
        fun providesGson(@DateTimeFormatQualifier dateTimeFormat: String): Gson =
            GsonBuilder().setDateFormat(dateTimeFormat).create()

        @JvmStatic
        @Provides
        @Reusable
        @DateTimeFormatQualifier
        fun providesDateTimeFormat(): String = "dd-MM-yyyy, HH:mm"

        @JvmStatic
        @Provides
        @Singleton
        fun providesAppDatabase(appContext: Context): AppDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            AppDatabase::class.java
        ).build()
    }

}