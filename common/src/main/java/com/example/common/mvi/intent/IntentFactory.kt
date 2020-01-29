package com.example.common.mvi.intent

import com.example.common.mvi.model.ModelState
import com.example.common.mvi.view.ViewEvent

interface IntentFactory<E: ViewEvent, MS: ModelState> {
    suspend fun toIntent(event: E): Intent<MS>
}