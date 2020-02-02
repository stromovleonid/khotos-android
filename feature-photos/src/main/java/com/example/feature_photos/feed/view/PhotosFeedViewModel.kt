package com.example.feature_photos.feed.view

import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.common.mvi.view.BaseViewModel
import com.example.feature_photos.feed.model.PhotosFeedModelState

class PhotosFeedViewModel(
    intentFactory: IntentFactory<PhotosFeedViewEvent, PhotosFeedModelState>,
    model: Model<PhotosFeedModelState>,
    stateMapper: StateMapper<PhotosFeedViewState, PhotosFeedModelState>
): BaseViewModel<PhotosFeedViewEvent, PhotosFeedViewState, PhotosFeedModelState>(
    intentFactory, model, stateMapper
)
