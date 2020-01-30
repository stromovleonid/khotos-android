package com.example.common.mvi.intent

import com.example.common.mvi.model.Model
import com.example.common.mvi.model.ModelState
import kotlinx.coroutines.CoroutineScope

interface Intent<S: ModelState> {
    suspend fun reduce(oldState: S): S

    fun sideEffect(
        state: S,
        model: Model<S>,
        scope: CoroutineScope
    ) = Unit

    companion object {
        fun <S: ModelState> create(reducer: suspend (S) -> S): Intent<S> = object : Intent<S> {
            override suspend fun reduce(oldState: S): S = reducer(oldState)
        }

        fun <S: ModelState> create(reducer: suspend (S) -> S, sideEffect: (S, Model<S>, CoroutineScope) -> Unit): Intent<S> = object : Intent<S> {
            override suspend fun reduce(oldState: S): S = reducer(oldState)

            override fun sideEffect(
                state: S,
                model: Model<S>,
                scope: CoroutineScope
            ) = sideEffect(state, model, scope)
        }
    }
}