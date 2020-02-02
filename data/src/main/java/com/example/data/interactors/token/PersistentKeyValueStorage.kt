package com.example.data.interactors.token

import android.content.Context
import com.orhanobut.hawk.Hawk
import javax.inject.Inject
import javax.inject.Singleton

interface PersistentKeyValueStorage {
    fun <V> put(key: String, value: V)

    fun <V> get(key: String): V?
}

@Singleton
class SecurePersistentKeyValueStorage @Inject constructor(context: Context):
    PersistentKeyValueStorage {

    init {
        Hawk.init(context).build()
    }

    override fun <V> put(key: String, value: V) {
        Hawk.put(key, value)
    }

    override fun <V> get(key: String): V? {
        return Hawk.get(key)
    }
}