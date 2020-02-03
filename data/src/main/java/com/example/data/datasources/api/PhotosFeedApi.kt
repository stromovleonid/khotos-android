package com.example.data.datasources.api

import com.example.data.model.dto.PhotosResponse
import retrofit2.Response

interface PhotosFeedApi {
    suspend fun getPhotosFeed(pageIndex: Int, pageSize: Int): Response<PhotosResponse>

    suspend fun getUserPhotos(userId: Long, pageIndex: Int, pageSize: Int): Response<PhotosResponse>
}