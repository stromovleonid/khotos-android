package com.example.data.interactor

import timber.log.Timber


private const val TOKEN = "token"

class TokenInteractor(private val storage: SecurePersistentKeyValueStorage) {
    fun saveToken(token: String) {
        Timber.i("token $token")
        storage.put(TOKEN, token)
    }
}