package com.example.data.model

sealed class UpdatePhotosRequestResult {
    class Error(e: Throwable): UpdatePhotosRequestResult()
    data class Success(val pageIndex: Int, val photosDownloadedCount: Int): UpdatePhotosRequestResult()
}