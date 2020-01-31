package com.example.data.interactor

private const val TOKEN = "token"

class TokenInteractor(private val storage: SecurePersistentKeyValueStorage) {

    private var cache: String? = null

    fun saveToken(token: String) = synchronized(this) {
        cache = token
        storage.put(TOKEN, token)
    }

    fun getToken(): String? = synchronized(this) {
        if (cache == null) {
            val token = storage.get<String>(TOKEN)
            cache = token
        }
        return cache
    }
}