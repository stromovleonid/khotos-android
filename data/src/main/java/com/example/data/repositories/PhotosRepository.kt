package com.example.data.repositories

import com.example.data.datasources.api.PhotosFeedApi
import com.example.data.datasources.db.PhotosDao
import com.example.data.di.PhotosPageSizeQualifier
import com.example.data.model.UpdatePhotosRequestResult
import com.example.data.model.dto.PhotosResponse
import com.example.data.model.photo.Photo
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(
    private val photosFeedApi: PhotosFeedApi,
    private val photosDao: PhotosDao,
    private val dispatchersProvider: DispatchersProvider,
    @PhotosPageSizeQualifier private val pageSize: Int
) : BaseApiRepository() {

    fun getPhotosFeed(): Flow<List<Photo>> = photosDao.getAll()

    suspend fun requestUserPhotosUpdate(userId: Long, lastPageLoaded: Int): UpdatePhotosRequestResult =
        requestPhotosPage(lastPageLoaded) { pageIndex ->
            photosFeedApi.getUserPhotos(
                userId,
                pageIndex,
                pageSize
            )
        }

    suspend fun requestPhotosFeedPage(lastPageLoaded: Int): UpdatePhotosRequestResult =
        requestPhotosPage(lastPageLoaded) { pageIndex ->
            photosFeedApi.getPhotosFeed(
                pageIndex,
                pageSize
            )
        }

    private suspend fun requestPhotosPage(lastPageLoaded: Int, block: suspend (Int) -> Response<PhotosResponse>) =
        withContext(dispatchersProvider.io) {
            val pageIndex = lastPageLoaded + 1
            val response = try {
                executeApiRequest {
                    block(pageIndex)
                }
            } catch (e: Exception) {
                return@withContext UpdatePhotosRequestResult.Error(e)
            }
            val newPhotos = response.map { Photo.fromResponse(it) }
            photosDao.addAll(newPhotos)
            return@withContext UpdatePhotosRequestResult.Success(
                pageIndex, newPhotos.isEmpty()
                        || newPhotos.size != pageSize
            )
        }

}
