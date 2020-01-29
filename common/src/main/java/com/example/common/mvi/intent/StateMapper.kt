package com.example.common.mvi.intent

import com.example.common.mvi.model.ModelState
import com.example.common.mvi.view.ViewState

interface StateMapper<VS: ViewState, MS: ModelState> {
    fun map(modelState: MS): VS
}