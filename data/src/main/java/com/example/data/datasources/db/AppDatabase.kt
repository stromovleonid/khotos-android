package com.example.data.datasources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.photo.Photo
import com.example.data.model.user.UserMetadata

@Database(
    entities = [Photo::class, UserMetadata::class],
    exportSchema = false,
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photosDao(): PhotosDao
    abstract fun usersDao(): UsersDao
}