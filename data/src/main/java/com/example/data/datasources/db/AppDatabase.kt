package com.example.data.datasources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.photo.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photosDao(): PhotosDao
}