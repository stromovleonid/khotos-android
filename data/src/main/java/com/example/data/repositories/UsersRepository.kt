package com.example.data.repositories

import com.example.data.datasources.api.UsersApi
import com.example.data.datasources.db.UsersDao
import com.example.data.model.user.UserMetadata
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val api: UsersApi,
    private val dao: UsersDao,
    private val dispatchersProvider: DispatchersProvider
) : BaseApiRepository() {

    suspend fun getUser(userId: Long) = withContext(dispatchersProvider.io) {
        dao.getUser(userId)
    }

    suspend fun saveUser(user: UserMetadata) = withContext(dispatchersProvider.io) {
        dao.add(user)
    }

    fun getUserWithPhotos(userId: Long) = dao.getUserPhotos(userId)
}