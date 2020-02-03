package com.example.common.mvi.model

import com.example.common.mvi.intent.Intent
import kotlinx.coroutines.flow.Flow

interface Model<MS : ModelState> {
    suspend fun consume(intent: Intent<MS>)
    fun observe(): Flow<MS>
    fun dispose()
}