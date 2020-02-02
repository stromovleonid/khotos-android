package com.example.common.di.app

import com.example.common.Constants
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.PhotosFeedApi
import com.example.data.datasources.db.AppDatabase
import com.example.data.datasources.db.PhotosDao
import com.example.data.di.PhotosPageSizeQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class PhotosModule {
    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun providesPhotosDao(db: AppDatabase): PhotosDao = db.photosDao()

        @JvmStatic
        @Provides
        @PhotosPageSizeQualifier
        fun providesPhotosPageSize(): Int = Constants.PHOTOS_PAGE_SIZE

        @JvmStatic
        @Provides
        @Singleton
        fun providesPhotosFeedApi(apiServiceAdapter: ApiServiceAdapter): PhotosFeedApi =
            apiServiceAdapter
    }
}
