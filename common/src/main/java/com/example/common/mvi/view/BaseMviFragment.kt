package com.example.common.mvi.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.common.mvi.model.ModelState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
abstract class BaseMviFragment<E : ViewEvent, VS : ViewState, MS : ModelState> : BaseNavigationFragment() {

    abstract val viewModel: BaseViewModel<E, VS, MS>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewEvents().onEach(viewModel::onEvent)
            .launchIn(lifecycleScope)

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    abstract fun viewEvents(): Flow<E>

    abstract fun render(state: VS)
}
