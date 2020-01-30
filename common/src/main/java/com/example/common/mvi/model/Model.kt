package com.example.common.mvi.model

import com.example.common.mvi.intent.Intent
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

interface Model<MS: ModelState> {
    suspend fun consume(intent: Intent<MS>)
    fun observe(): Flow<MS>
    fun dispose()
}

@ExperimentalCoroutinesApi
@FlowPreview
abstract class BaseModel<S: ModelState>(
    initialState: S,
    dispatchersProvider: DispatchersProvider):
    Model<S> {

    private val intents: Channel<Intent<S>> = Channel()
    private val data = ConflatedBroadcastChannel(initialState)

    private val modelScope = CoroutineScope(dispatchersProvider.def + SupervisorJob())

    init {
        modelScope.launch {
            while (isActive) {
                val newIntent = intents.receive()
                val newState = newIntent.reduce(data.value)
                data.offer(newState)
                newIntent.sideEffect(newState)?.let {
                    consume(it)
                }
            }
        }
    }

    override suspend fun consume(intent: Intent<S>) {
        intents.send(intent)
    }

    override fun observe() = data.asFlow()

    override fun dispose() {
        intents.close()
        data.close()
        modelScope.cancel()
    }
}