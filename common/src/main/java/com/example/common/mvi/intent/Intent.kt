package com.example.common.mvi.intent

import com.example.common.mvi.model.ModelState
import kotlinx.coroutines.channels.Channel

interface Intent<S: ModelState> {
    suspend fun reduce(oldState: S): S

    suspend fun sideEffect(state: S):   Intent<S>? = null

    companion object {
        fun <S: ModelState> create(reducer: suspend (S) -> S): Intent<S> = object : Intent<S> {
            override suspend fun reduce(oldState: S): S = reducer(oldState)
        }

        fun <S: ModelState> create(reducer: suspend (S) -> S, sideEffect: suspend (S) -> Intent<S>): Intent<S> = object : Intent<S> {
            override suspend fun reduce(oldState: S): S = reducer(oldState)

            override suspend fun sideEffect(state: S) = sideEffect(state)
        }
    }
}