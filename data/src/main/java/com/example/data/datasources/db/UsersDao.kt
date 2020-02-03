package com.example.data.datasources.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.model.user.UserMetadata
import com.example.data.model.user.UserWithPhotos
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("select * from usermetadata where id == :userId")
    fun getUser(userId: Long): Flow<UserMetadata>

    @Transaction
    @Query("SELECT * FROM usermetadata where :userId = id")
    fun getUserPhotos(userId: String): Flow<UserWithPhotos>
}