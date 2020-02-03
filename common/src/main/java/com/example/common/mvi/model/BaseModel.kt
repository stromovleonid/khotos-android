package com.example.common.mvi.model

import com.example.common.mvi.intent.Intent
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

@ExperimentalCoroutinesApi
@FlowPreview
abstract class BaseModel<S : ModelState>(
    initialState: S,
    dispatchersProvider: DispatchersProvider
) : Model<S> {

    private val intents: Channel<Intent<S>> = Channel()

    private val modelScope = CoroutineScope(dispatchersProvider.def + SupervisorJob())

    init {
        modelScope.launch {
            var currentState = initialState
            while (isActive) {
                val newIntent = intents.receive()
                currentState = newIntent.reduce(currentState)
                submitState(currentState)
                newIntent.sideEffect(currentState, this@BaseModel, modelScope)
            }
        }
    }

    abstract fun submitState(newState: S)

    override suspend fun consume(intent: Intent<S>) {
        intents.send(intent)
    }

    override fun dispose() {
        intents.close()
        modelScope.cancel()
    }
}