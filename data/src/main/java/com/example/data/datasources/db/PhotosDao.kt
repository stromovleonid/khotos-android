package com.example.data.datasources.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.photo.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(photos: List<Photo>)

    @Query("SELECT * FROM photo")
    fun getAll(): Flow<List<Photo>>
}