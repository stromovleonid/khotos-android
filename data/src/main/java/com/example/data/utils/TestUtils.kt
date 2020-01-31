package com.example.data.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object TestUtils {
    suspend fun testPause() = delay(20)

    @ExperimentalCoroutinesApi
    inline fun <T> CoroutineScope.testObserveFlow(flow: Flow<T>, crossinline callback: (T) -> Unit) =
        flow
            .onEach {
                callback(it)
            }.launchIn(this)
}

object TestDispatchersProviderImpl: DispatchersProvider {
    override val io = Dispatchers.Default
    override val main = Dispatchers.Default
    override val def = Dispatchers.Default
}