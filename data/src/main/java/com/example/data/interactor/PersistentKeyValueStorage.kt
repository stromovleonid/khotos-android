package com.example.data.interactor

import android.content.Context
import com.orhanobut.hawk.Hawk

interface PersistentKeyValueStorage {
    fun <V> put(key: String, value: V)

    fun <V> get(key: String): V?
}

class SecurePersistentKeyValueStorage(context: Context): PersistentKeyValueStorage {

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