package com.example.common.mvi.intent

import com.example.common.mvi.model.ModelState

interface Intent<S: ModelState> {
    suspend fun reduce(oldState: S): S

    companion object {
        fun <S: ModelState> create(block: suspend (S) -> S): Intent<S> = object : Intent<S> {
            override suspend fun reduce(oldState: S): S = block(oldState)
        }
    }
}