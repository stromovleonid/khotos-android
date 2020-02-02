package com.example.feature_photos.feed.model

import com.example.common.mvi.intent.Intent
import com.example.common.mvi.model.Model
import com.example.data.datasources.db.PhotosDao
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
@FlowPreview
open class PhotosFeedModel(
    private val initialState: PhotosFeedModelState,
    private val photosDao: PhotosDao,
    dispatchersProvider: DispatchersProvider,
    private val pageSize: Int
) : Model<PhotosFeedModelState> {

    private val intents: Channel<Intent<PhotosFeedModelState>> = Channel()

    private val modelScope = CoroutineScope(dispatchersProvider.def + SupervisorJob())

    init {
        modelScope.launch {
            var currentState = initialState
            while (isActive) {
                val newIntent = intents.receive()
                currentState = newIntent.reduce(currentState)
                newIntent.sideEffect(currentState, this@PhotosFeedModel, modelScope)
            }
        }
    }

    override suspend fun consume(intent: Intent<PhotosFeedModelState>) {
        intents.send(intent)
    }

    override fun observe() = photosDao.getAll().map {
        PhotosFeedModelState(
            it,
            false,
            0,
            false

        )
    }

    override fun dispose() {
        intents.close()
        modelScope.cancel()
    }
}