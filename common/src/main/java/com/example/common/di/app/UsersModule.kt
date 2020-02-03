package com.example.common.di.app

import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.UsersApi
import com.example.data.datasources.db.AppDatabase
import com.example.data.datasources.db.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class UsersModule {
    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun providesPhotosDao(db: AppDatabase): UsersDao = db.usersDao()

        @JvmStatic
        @Provides
        @Singleton
        fun providesPhotosFeedApi(apiServiceAdapter: ApiServiceAdapter): UsersApi =
            apiServiceAdapter
    }
}
