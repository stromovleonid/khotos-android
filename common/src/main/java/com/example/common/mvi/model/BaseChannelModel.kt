package com.example.common.mvi.model

import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
@FlowPreview
open class BaseChannelModel<S : ModelState>(
    initialState: S,
    dispatchersProvider: DispatchersProvider
) : BaseModel<S>(initialState, dispatchersProvider) {

    private val data = ConflatedBroadcastChannel(initialState)

    override fun submitState(newState: S) {
        data.offer(newState)
    }

    override fun observe() = data.asFlow()

    override fun dispose() {
        super.dispose()
        data.close()
    }
}