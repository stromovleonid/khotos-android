package com.example.feature_splash.model

import com.example.common.mvi.model.BaseChannelModel
import com.example.data.utils.DispatchersProvider
import com.example.feature_splash.view.SplashViewModelState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class SplashModel(dispatchersProvider: DispatchersProvider) : BaseChannelModel<SplashViewModelState>(
    SplashViewModelState.None,
    dispatchersProvider
)