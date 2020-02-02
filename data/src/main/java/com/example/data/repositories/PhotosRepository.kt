package com.example.data.repositories

import com.example.data.datasources.api.PhotosFeedApi
import com.example.data.datasources.db.PhotosDao
import com.example.data.model.UpdatePhotosRequestResult
import com.example.data.model.photo.Photo
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PhotosRepository(
    private val photosFeedApi: PhotosFeedApi,
    private val photosDao: PhotosDao,
    private val dispatchersProvider: DispatchersProvider,
    private val pageSize: Int
) : BaseApiRepository() {

    fun getPhotosFeed(): Flow<List<Photo>> = photosDao.getAll()

    suspend fun requestPhotosFeedPage(lastPageLoaded: Int): UpdatePhotosRequestResult = withContext(dispatchersProvider.io) {
        val pageIndex = lastPageLoaded + 1
        val response = try {
            executeApiRequest {
                photosFeedApi.getPhotosFeed(
                    pageIndex,
                    pageSize
                )
            }
        } catch (e: Exception) {
            return@withContext UpdatePhotosRequestResult.Error(e)
        }
        val newPhotos = response.map { Photo.fromResponse(it) }
        photosDao.addAll(newPhotos)
        return@withContext UpdatePhotosRequestResult.Success(pageIndex, newPhotos.size)
    }

}
