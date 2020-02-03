package com.example.data.repositories

import com.example.data.datasources.api.UsersApi
import com.example.data.datasources.db.UsersDao
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.withContext

class UsersRepository(
    private val api: UsersApi,
    private val dao: UsersDao,
    private val dispatchersProvider: DispatchersProvider
) : BaseApiRepository() {

    suspend fun getUser(userId: Long) = withContext(dispatchersProvider.io) {
        dao.getUser(userId)
    }

    fun getUserWithPhotos(userId: Long) = dao.getUserPhotos(userId)
}