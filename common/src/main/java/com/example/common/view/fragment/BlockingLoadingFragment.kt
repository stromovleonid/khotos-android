package com.example.common.view.fragment

import android.os.Handler
import com.example.common.mvi.model.ModelState
import com.example.common.mvi.view.BaseMviFragment
import com.example.common.mvi.view.ViewEvent
import com.example.common.mvi.view.ViewState
import com.example.common.view.dialogs.LoadingDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BlockingLoadingFragment<E : ViewEvent, VS : ViewState, MS : ModelState> :
    BaseMviFragment<E, VS, MS>() {

    private val loadingDialog = LoadingDialog()

    private var wasAdded = false

    private val handler = Handler()

    protected fun showLoading() = handler.post {
        if (loadingDialog.dialog?.isShowing != true && !loadingDialog.isAdded && !wasAdded) {
            loadingDialog.show(parentFragmentManager, "loading")
            wasAdded = true
        }
    }

    protected fun hideLoading() = handler.postDelayed({
        if (loadingDialog.isAdded && wasAdded) {
            loadingDialog.dismiss()
            wasAdded = false
        }
    }, 300L)
}