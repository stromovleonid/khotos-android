package com.example.common.di.app

import com.example.common.Constants
import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.PhotosFeedApi
import com.example.data.datasources.db.AppDatabase
import com.example.data.datasources.db.PhotosDao
import com.example.data.repositories.PhotosRepository
import com.example.data.utils.DispatchersProvider
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
        @Singleton
        fun providesPhotosRepository(api: PhotosFeedApi,
                                     dao: PhotosDao,
                                     dispatchersProvider: DispatchersProvider): PhotosRepository {
            return PhotosRepository(api, dao, dispatchersProvider, Constants.PHOTOS_PAGE_SIZE)
        }

        @JvmStatic
        @Provides
        @Singleton
        fun providesPhotosFeedApi(apiServiceAdapter: ApiServiceAdapter): PhotosFeedApi =
            apiServiceAdapter
    }
}
