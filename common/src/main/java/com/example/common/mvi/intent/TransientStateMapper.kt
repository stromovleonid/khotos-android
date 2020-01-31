package com.example.common.mvi.intent

import com.example.common.mvi.model.ModelState
import com.example.common.mvi.view.ViewState

class TransientStateMapper<S>: StateMapper<S, S> where S: ViewState, S: ModelState  {
    override fun map(modelState: S): S = modelState

}