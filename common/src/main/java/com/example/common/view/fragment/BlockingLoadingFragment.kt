package com.example.common.view.fragment

import com.example.common.mvi.model.ModelState
import com.example.common.mvi.view.BaseFragment
import com.example.common.mvi.view.ViewEvent
import com.example.common.mvi.view.ViewState
import com.example.common.view.dialogs.LoadingDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BlockingLoadingFragment<E: ViewEvent, VS: ViewState, MS: ModelState> : BaseFragment<E, VS, MS>() {

    private val loadingDialog = LoadingDialog()

    private var wasAdded = false

    protected fun showLoading() {
        if (loadingDialog.dialog?.isShowing != true && !loadingDialog.isAdded && !wasAdded) {
            loadingDialog.show(parentFragmentManager, "loading")
            wasAdded = true
        }
    }

    protected fun hideLoading() {
        if (loadingDialog.isAdded && wasAdded) {
            loadingDialog.dismiss()
            wasAdded = false
        }
    }
}