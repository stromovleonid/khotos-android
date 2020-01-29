package com.example.common.mvi.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.common.mvi.model.ModelState

abstract class BaseViewModel<E: ViewEvent, VS: ViewState, MS: ModelState>(
    private val intentFactory: IntentFactory<E, MS>,
    private val model: Model<MS>,
    stateMapper: StateMapper<VS, MS>
) : ViewModel() {

    val viewState = model.observe().asLiveData().map(stateMapper::map)

    suspend fun onEvent(event: E) {
        model.consume(intentFactory.toIntent(event))
    }

    override fun onCleared() {
        super.onCleared()
        model.dispose()
    }
}